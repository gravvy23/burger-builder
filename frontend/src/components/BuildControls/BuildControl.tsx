import React from "react";
import { IonButton, IonText } from "@ionic/react";
import "./BuildControl.css";

type Props = {
  disabled: boolean;
  label: String;
  addIngredient: () => void;
  removeIngredient: () => void;
};

export const BuildControl: React.FC<Props> = ({
    disabled,
  label,
  addIngredient,
  removeIngredient,
}) => (
  <div className="BuildControl">
    <IonText className="BuildControl Text" color="light">
      <h3 className="ControlLabel">{label}</h3>
    </IonText>
    <IonButton disabled={disabled} onClick={removeIngredient}>-1</IonButton>
    <IonButton onClick={addIngredient}>+1</IonButton>
  </div>
);
