import React, { useContext } from "react";
import { Burger } from "./Burger/Burger";
import { BuildControls } from "./BuildControls/BuildControls";
import { SummaryModal } from "./UI/SummaryModal";
import {
  getIngredients,
  IngredientStore,
} from "../api/api";
import { IngredientType, Ingredients } from "../types";
import { IngredientContext } from "../context/ingredientContext";
import { UserContext } from "../context/UserContext";
import { useHistory } from "react-router";

export const START_PRICE = 2.5;

const getIngredientPrice = (store: IngredientStore, type: IngredientType) =>
  store.find(({ name }) => name === type)?.price ?? 0;

const getPrice = (ingredients: Ingredients, store: IngredientStore) =>
  START_PRICE +
  Object.keys(ingredients).reduce(
    (total, ingredient) =>
      total +
      ingredients[ingredient as IngredientType]! *
        getIngredientPrice(store, ingredient as IngredientType),
    0
  );

export const BurgerBuilder = () => {
  const { user } = React.useContext(UserContext);
  const { ingredients, changeIngredients, ingredientStore, setIngredientStore, price, setPrice } = useContext(IngredientContext);
  const [isModalOpened, toggleModal] = React.useState(false);
  const history = useHistory();

  React.useEffect(() => {
    const getAndSaveIngredients = async () => {
      if (ingredientStore.length == 0) {
        const fetchedData = await getIngredients();
        setIngredientStore(fetchedData);
        changeIngredients(
          fetchedData.reduce<Ingredients>((store, ingredient) => {
            store[ingredient.name] = 0;
            return store;
          }, {})
        );
      } 
      setPrice(getPrice(ingredients, ingredientStore));
    };
    getAndSaveIngredients();
  }, []);

  const addIngredient = (type: keyof Ingredients) => () => {
    changeIngredients(ingredients => ({
      ...ingredients,
      [type]: (ingredients[type] ?? 0) + 1,
    }));
    setPrice(
      (price) => price + getIngredientPrice(ingredientStore, type)
    );
  };

  const removeIngredient = (type: keyof Ingredients) => () => {
    changeIngredients((ingredients) => ({
      ...ingredients,
      [type]: ingredients[type] === 0 ? 0 : ingredients[type]! - 1,
    }));
    setPrice(
      (price) => price - getIngredientPrice(ingredientStore, type)
    );
  };

  return (
    <>
      <Burger ingredients={ingredients} />
      <BuildControls
        controls={ingredientStore.map(
          ({ name: type, displayname: label }) => ({ type, label })
        )}
        price={price}
        ingredients={ingredients}
        addIngredients={addIngredient}
        removeIngredients={removeIngredient}
        openModal={() => {
          if (user.id) {
            toggleModal(true);
          } else {
            history.push('/page/Authenticate');
          }
        }}
        isAuthenticated={!!user.id}
      />
      <SummaryModal
        ingredients={ingredients}
        isOpen={isModalOpened}
        onDidDismiss={() => {
          toggleModal(false);
        }}
        close={() => {
          toggleModal(false);
        }}
        price={price}
      />
    </>
  );
};
