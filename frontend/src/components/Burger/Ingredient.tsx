import React from "react";
import "./Ingredient.css";
import { IngredientType } from "../../types";

type BreadType =   | "bread-top"
| "bread-bottom";

type Props = {
  type: IngredientType | BreadType;
};
export const Ingredient : React.FC<Props> = ({ type }) => {
  switch (type) {
    case "bread-bottom":
      return <div className="BreadBottom" />;
    case "bread-top":
      return (
        <div className="BreadTop">
          <div className="face" />
          <div className="Seeds1" />
          <div className="Seeds2" />
        </div>
      );
    case "meat":
      return <div className="Meat" />;
    case "cheese":
      return (
        <div className="Cheese">
          <div className="Melt" />
        </div>
      );
    case "salad":
      return <div className="Salad" />;
    case "tomato":
      return <div className="Tomato" />;
    case "onion":
      return <div className="Onion" />;
    case "bacon":
      return <div className="Bacon" />;
    case "cucumber":
      return <div className="Cucumber" />;
    default:
      return null;
  }
};
