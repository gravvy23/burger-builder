import React from "react";
import { IonContent, IonHeader, IonButton } from "@ionic/react";
import "./CheckoutSummary.css";
import { Burger } from "../Burger/Burger";
import { Ingredients } from "../../types";

type Props = {
  ingredients: Ingredients;
  onSuccess: () => void;
  onCancel: () => void;
};

export const CheckoutSummary: React.FC<Props> = ({
  ingredients,
  onCancel,
  onSuccess,
}) => {
  return (
    <div className="CheckoutSummary">
      <h2>We hope it tastes well!</h2>
      <div className="CheckoutBurgerContainer">
        <Burger ingredients={ingredients} />
      </div>
      <IonButton color="danger" onClick={onCancel}>
        cancel
      </IonButton>
      <IonButton color="success" onClick={onSuccess}>
        continue
      </IonButton>
    </div>
  );
};
