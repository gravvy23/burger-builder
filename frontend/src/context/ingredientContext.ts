import React from "react";
import { Ingredients } from "../types";
import { IngredientStore } from "../api/api";

type IngredientContext = {
  ingredients: Ingredients;
  changeIngredients: React.Dispatch<React.SetStateAction<Ingredients>>;
  price: number;
  setPrice: React.Dispatch<React.SetStateAction<number>>;
  ingredientStore: IngredientStore;
  setIngredientStore: React.Dispatch<React.SetStateAction<IngredientStore>>;
};

export const IngredientContext = React.createContext<IngredientContext>({
  ingredients: {},
  changeIngredients: () => {},
  price: 0,
  setPrice: () => {},
  ingredientStore: [],
  setIngredientStore: () => {},
});
