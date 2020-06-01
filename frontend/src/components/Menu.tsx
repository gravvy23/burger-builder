import {
  IonContent,
  IonIcon,
  IonItem,
  IonLabel,
  IonList,
  IonListHeader,
  IonMenu,
  IonMenuToggle,
} from "@ionic/react";

import React, { useContext } from "react";
import { useLocation } from "react-router-dom";
import {
  personSharp,
  personOutline,
  heartOutline,
  heartSharp,
  mailUnreadOutline,
  mailUnreadSharp,
  fastFoodOutline,
  fastFoodSharp,
} from "ionicons/icons";
import "./Menu.css";
import { UserContext } from "../context/UserContext";

interface AppPage {
  url: string;
  iosIcon: string;
  mdIcon: string;
  title: string;
  onlyLogged?: boolean;
  ifLogged?: boolean;
}

const appPages: AppPage[] = [
  {
    title: "Sign up/Log in",
    url: "/page/Authenticate",
    iosIcon: personOutline,
    mdIcon: personSharp,
    ifLogged: true,
  },
  {
    title: "Logout",
    url: "/logout",
    iosIcon: personOutline,
    mdIcon: personSharp,
    onlyLogged: true,
  },
  {
    title: "Buid your burger",
    url: "/page/Burger",
    iosIcon: fastFoodOutline,
    mdIcon: fastFoodSharp,
  },
  {
    title: "Orders",
    url: "/page/Orders",
    iosIcon: mailUnreadOutline,
    mdIcon: mailUnreadSharp,
    onlyLogged: true,
  },
];

const Menu: React.FC = () => {
  const { user } = useContext(UserContext);
  const location = useLocation();

  return (
    <IonMenu contentId="main" type="overlay">
      <IonContent>
        <IonList id="inbox-list">
          <IonListHeader>Build your own burgrer</IonListHeader>
          {appPages
            .filter(
              ({ onlyLogged, ifLogged }) =>
                (onlyLogged && user.id) ||
                (ifLogged && !user.id) ||
                (!onlyLogged && !ifLogged)
            )
            .map((appPage, index) => {
              return (
                <IonMenuToggle key={index} autoHide={false}>
                  <IonItem
                    className={
                      location.pathname === appPage.url ? "selected" : ""
                    }
                    routerLink={appPage.url}
                    routerDirection="none"
                    lines="none"
                    detail={false}
                  >
                    <IonIcon slot="start" icon={appPage.iosIcon} />
                    <IonLabel>{appPage.title}</IonLabel>
                  </IonItem>
                </IonMenuToggle>
              );
            })}
        </IonList>
      </IonContent>
    </IonMenu>
  );
};

export default Menu;
