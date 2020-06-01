import React from "react";
import { Ingredient } from "./Ingredient";
import { v4 as uuidv4 } from 'uuid';
import { Ingredients, IngredientType } from "../../types";
import "./Burger.css";

type Props = {
  ingredients: Ingredients;
};

export const Burger: React.FC<Props> = ({ ingredients }) => (
  <div className="Burger">
    <Ingredient type="bread-top" />
    {Object.entries(ingredients).map(([key, value]) =>
      Array(value)
        .fill(0)
        .map(() => <Ingredient key={uuidv4()} type={key as IngredientType} />)
    )}
    <Ingredient type="bread-bottom" />
  </div>
);
