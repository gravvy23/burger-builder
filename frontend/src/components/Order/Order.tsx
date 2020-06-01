import React from "react";
import { OrderType, Ingredients } from "../../types";
import "./Order.css";
import { IonCard } from "@ionic/react";

type Props = {
  order: OrderType;
};

const mapIngredientsToString = (ingredients: Ingredients) =>
  Object.entries(ingredients).map(([key, value]) => <IonCard className="OrderTag" key={key}>{key}: ({value})</IonCard>);

export const Order: React.FC<Props> = ({ order: { price, ingredients } }) => {
  return (
    <div className="Order">
      <div>Ingredients: 
          <div className="OrderTags">{mapIngredientsToString(ingredients)}</div>
     </div>
      <p>
        Price: <strong>{price.toFixed(2)}$</strong>
      </p>
    </div>
  );
};
