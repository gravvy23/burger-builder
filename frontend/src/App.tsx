import Menu from "./components/Menu";
import Page from "./pages/Page";
import React from "react";
import { IonApp, IonRouterOutlet, IonSplitPane } from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";
import { Redirect, Route } from "react-router-dom";
import { IngredientContext } from "./context/ingredientContext";

/* Core CSS required for Ionic components to work properly */
import "@ionic/react/css/core.css";

/* Basic CSS for apps built with Ionic */
import "@ionic/react/css/normalize.css";
import "@ionic/react/css/structure.css";
import "@ionic/react/css/typography.css";

/* Optional CSS utils that can be commented out */
import "@ionic/react/css/padding.css";
import "@ionic/react/css/float-elements.css";
import "@ionic/react/css/text-alignment.css";
import "@ionic/react/css/text-transformation.css";
import "@ionic/react/css/flex-utils.css";
import "@ionic/react/css/display.css";

/* Theme variables */
import "./theme/variables.css";
import { Ingredients, UserType } from "./types";
import { IngredientStore, getUser } from "./api/api";
import { UserContext } from "./context/UserContext";
import { Logout } from "./components/Logout";

const App: React.FC = () => {
  const [ingredients, changeIngredients] = React.useState<Ingredients>({});
  const [ingredientStore, setIngredientStore] = React.useState<IngredientStore>(
    []
  );
  const [price, setPrice] = React.useState(0);
  const [user, changeUser] = React.useState<UserType>({});

  React.useEffect(() => {
    const userId = localStorage.getItem('usrid');
    if (userId) {
      changeUser({id:userId});
      const getAndSaveUser = async () => {
        const fetchedUser = await getUser(userId);
        changeUser(fetchedUser);
      }
      getAndSaveUser();
    }
  },[]);

  return (
    <IngredientContext.Provider
      value={{
        ingredients,
        changeIngredients,
        ingredientStore,
        setIngredientStore,
        price,
        setPrice,
      }}
    >
      <UserContext.Provider value={{ user, changeUser }}>
        <IonApp>
          <IonReactRouter>
            <IonSplitPane contentId="main">
              <Menu />
              <IonRouterOutlet id="main">
                <Route path="/page/:name/:rest" component={Page} exact />
                <Route path="/page/:name" component={Page} exact />
                <Route path="/logout" component={Logout} exact />
                <Redirect from="/" to="/page/Burger" exact />
              </IonRouterOutlet>
            </IonSplitPane>
          </IonReactRouter>
        </IonApp>
      </UserContext.Provider>
    </IngredientContext.Provider>
  );
};

export default App;
