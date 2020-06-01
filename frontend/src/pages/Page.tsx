import {
  IonButtons,
  IonContent,
  IonHeader,
  IonMenuButton,
  IonPage,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import React from "react";
import { useParams } from "react-router";
import "./Page.css";
import { BurgerBuilder } from "../components/BurgerBuilder";
import { Checkout } from "../components/Checkout";
import { ContactData } from "../components/Order/ContactData";
import { Orders } from "../components/Orders";
import { Auth } from "../components/Auth";

type RouteName = "Burger" | "Checkout" | "Orders" | "Authenticate";
type Rest = "contact-data" | "";

const mapComponentByName = (name: RouteName, rest?: Rest) => {
  switch (name) {
    case "Burger":
      return <BurgerBuilder />;
    case "Orders":
      return <Orders />;
    case "Authenticate":
      return <Auth />;
    case "Checkout":
      return rest === "contact-data" ? <ContactData /> : <Checkout />;
  }
};
const Page: React.FC = () => {
  const { name, rest } = useParams<{ name: RouteName; rest?: Rest }>();
  const content = mapComponentByName(name, rest);

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonButtons slot="start">
            <IonMenuButton />
          </IonButtons>
          <IonTitle>{name}</IonTitle>
        </IonToolbar>
      </IonHeader>

      <IonContent>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">{name}</IonTitle>
          </IonToolbar>
        </IonHeader>
        {content}
      </IonContent>
    </IonPage>
  );
};

export default Page;
