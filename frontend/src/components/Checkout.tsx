import React, { useState } from "react";
import { useHistory, useLocation } from "react-router";
import { CheckoutSummary } from "./Order/CheckoutSummary";
import { Ingredients, IngredientType } from "../types";

export const Checkout: React.FC = () => {
  const history = useHistory();
  const location = useLocation();
  const [ingredients, setIngredients] = useState<Ingredients>({});
  React.useEffect(() => {
    const query = new URLSearchParams(location.search);
    const retreivedIngredients: Ingredients = {};
    query.forEach((value, key) => {
      retreivedIngredients[key as IngredientType] = Number.parseInt(value);
    });
    setIngredients(retreivedIngredients);
  }, []);

  const onCancelHandler = () => {
    history.goBack();
  };

  const onSuccessHandler = () => {
    history.replace("/page/Checkout/contact-data");
  };

  return (
    <div>
      <CheckoutSummary
        onCancel={onCancelHandler}
        onSuccess={onSuccessHandler}
        ingredients={ingredients}
      />
    </div>
  );
};
