import React from "react";
import { IonModal, IonInput, IonButton, IonLoading } from "@ionic/react";
import { ContactForm } from "../types";
import { checkValidity } from "./Order/ContactData";
import { createUser, signIn } from "../api/api";
import { UserContext } from "../context/UserContext";
import "./Auth.css";
import { Redirect } from "react-router";

const initialControls: ContactForm = {
  name: {
    elementConfig: {
      type: "text",
      placeholder: "Name",
    },
    value: "",
    validation: {
      required: true,
    },
    valid: false,
    touched: false,
    login: false,
  },
  surname: {
    elementConfig: {
      type: "text",
      placeholder: "Surname",
    },
    value: "",
    validation: {
      required: true,
    },
    valid: false,
    touched: false,
    login: false,
  },
  email: {
    elementConfig: {
      type: "email",
      placeholder: "Mail Address",
    },
    value: "",
    validation: {
      email: true,
      required: true,
    },
    valid: false,
    touched: false,
    login: true,
  },
  password: {
    elementConfig: {
      type: "password",
      placeholder: "Password",
    },
    value: "",
    validation: {
      minLength: 6,
      required: true,
    },
    valid: false,
    touched: false,
    login: true,
  },
};

export const Auth: React.FC = () => {
  const { changeUser, user } = React.useContext(UserContext);
  const [controls, setControls] = React.useState(initialControls);
  const [formIsValid, toggleFormValidation] = React.useState(false);
  const [isSignup, togggleSignup] = React.useState(true);
  const [loading, setLoading] = React.useState(false);
  React.useEffect(() => {

  }, [isSignup]);
  
  const formElementsArray = [];
  for (let key in controls) {
    if (isSignup || controls[key].login) {
      formElementsArray.push({
        id: key,
        config: controls[key],
      });
    }
  }

  const inputChangedHandler = (
    inputIdentifier: string,
    value?: string | null
  ) => {
    const updatedOrderForm = {
      ...controls,
    };
    const updatedFormElement = {
      ...updatedOrderForm[inputIdentifier],
    };
    updatedFormElement.value = value ?? "";
    updatedFormElement.valid = checkValidity(
      updatedFormElement.value,
      updatedFormElement.validation
    );
    updatedFormElement.touched = true;
    updatedOrderForm[inputIdentifier] = updatedFormElement;

    let formIsValid = true;
    for (let inputIdentifier in updatedOrderForm) {
        const isInputValid = isSignup ? updatedOrderForm[inputIdentifier].valid : (!updatedOrderForm[inputIdentifier].login || updatedOrderForm[inputIdentifier].valid)
      formIsValid = isInputValid && formIsValid;
    }
    setControls(updatedOrderForm);
    toggleFormValidation(formIsValid);
  };

  const submitHandler = () => {
    setLoading(true);
    if (isSignup) {
      createUser(
        controls.email!.value,
        controls.password!.value,
        controls.name!.value,
        controls.surname!.value
      )
        .then((user) => {
          changeUser(user);
          setLoading(false);
        })
        .catch((err) => {
          setLoading(false);
          console.error(err);
        });
    } else {
      signIn(controls.email!.value, controls.password!.value)
        .then((user) => {
          changeUser(user);
          setLoading(false);
        })
        .catch((err) => {
          setLoading(false);
          console.error(err);
        });
    }
  };

  const switchAuthMode = () => {
    togggleSignup((prev) => !prev);
  };

  return (
    <IonModal isOpen cssClass="AuthModal">
      <div className="Auth">
        {user.id && <Redirect to="/"/>} 
        <form>
          {formElementsArray.map((formElement) => (
            <IonInput
              className="FormInput"
              key={formElement.id}
              type={formElement.config.elementConfig.type}
              placeholder={formElement.config.elementConfig.placeholder}
              value={formElement.config.value}
              color={
                formElement.config.valid || !formElement.config.touched
                  ? "light"
                  : "primary"
              }
              onIonChange={(event) =>
                inputChangedHandler(formElement.id, event.detail.value)
              }
            />
          ))}
          <IonButton
            style={{ width: "130px" }}
            color="success"
            onClick={submitHandler}
            disabled={!formIsValid}
          >
            submit
          </IonButton>
        </form>
        <IonButton color="danger" onClick={switchAuthMode}>
          {isSignup ? "switch to login" : "switch to signup"}
        </IonButton>
      </div>
      <IonLoading isOpen={loading} message={"Checking..."} />
    </IonModal>
  );
};
