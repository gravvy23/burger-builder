import React from "react";
import { useHistory } from "react-router-dom";
import {
  IonModal,
  IonText,
  IonList,
  IonLabel,
  IonItem,
  IonFooter,
  IonButton,
  IonSpinner,
} from "@ionic/react";
import { Ingredients, IngredientType } from "../../types";
import "./SummaryModal.css";

type Props = Pick<
  React.ComponentProps<typeof IonModal>,
  "isOpen" | "onDidDismiss"
> & {
  ingredients: Ingredients;
  close: () => void;
  price: number;
};

export const SummaryModal: React.FC<Props> = ({
  isOpen,
  onDidDismiss,
  ingredients,
  close,
  price,
}) => {
  const history = useHistory();

  const onSuccessHandler = () => {
    const queryString = Object.entries(ingredients)
      .reduce<string[]>(
        (params, ing) => [
          ...params,
          `${encodeURIComponent(ing[0])}=${encodeURIComponent(ing[1]!)}`,
        ],
        []
      )
      .join("&");

    history.push({
      pathname: "/page/Checkout",
      search: "?" + queryString,
    });
  };

  return (
    <IonModal isOpen={isOpen} onDidDismiss={onDidDismiss} swipeToClose>
      <IonText color="light" className="ModalContent">
        <h3 className="ModalContentTitle">Your Order</h3>
        <p>A delicious burger with the following ingredients:</p>
        <IonList className="ModalList">
          {Object.entries(ingredients)
            .filter((pair) => pair[1] && pair[1] > 0)
            .map(([ingredient, count]) => (
              <IonItem className="ModalItem" key={ingredient}>
                <IonLabel>
                  {ingredient}: {count}
                </IonLabel>
              </IonItem>
            ))}
        </IonList>
        <p>
          <strong>Total Price: {price.toFixed(2)}$</strong>
        </p>
        <p>Continue checkout?</p>
      </IonText>
      <IonFooter className="ModalFooter">
        <IonButton style={{ width: "130px" }} color="danger" onClick={close}>
          cancel
        </IonButton>
        <IonButton
          style={{ width: "130px" }}
          color="success"
          onClick={onSuccessHandler}
        >
          continue
        </IonButton>
      </IonFooter>
    </IonModal>
  );
};
