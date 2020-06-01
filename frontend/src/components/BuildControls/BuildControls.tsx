import React from "react";
import "./BuildControls.css";
import { BuildControl } from "./BuildControl";
import { IonText, IonButton } from "@ionic/react";
import { START_PRICE } from "../BurgerBuilder";
import { IngredientType, Ingredients } from "../../types";

type Control = {
  label: string;
  type: IngredientType;
};

type Props = {
  controls: Array<Control>;
  price: number;
  ingredients: Ingredients;
  isAuthenticated: boolean;
  openModal: () => void;
  addIngredients: (type: IngredientType) => () => void;
  removeIngredients: (type: IngredientType) => () => void;
};

export const BuildControls: React.FC<Props> = ({
  controls,
  price,
  ingredients,
  isAuthenticated,
  openModal,
  addIngredients,
  removeIngredients,
}) => (
  <>
    <IonText className="TotalPrice">
      <h2>
        Current price: <strong>{`${Math.abs(price).toFixed(2)} $`}</strong>
      </h2>
    </IonText>
    <div className="BuildControls">
      {controls.map(({ label, type }) => (
        <BuildControl
          addIngredient={addIngredients(type)}
          removeIngredient={removeIngredients(type)}
          key={type}
          label={label}
          disabled={ingredients[type] === 0}
        />
      ))}
    </div>
    <div className="OrderButton">
      <IonButton
        disabled={price <= START_PRICE}
        color="success"
        onClick={openModal}
      >
        {isAuthenticated ? 'order now' : 'sign up to order'}
      </IonButton>
    </div>
  </>
);
