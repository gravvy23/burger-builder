import React, { useContext } from "react";
import { ContactForm, ValidationRules } from "../../types";
import { IonInput, IonButton, IonSpinner } from "@ionic/react";
import "./ContactData.css";
import { IngredientContext } from "../../context/ingredientContext";
import { IngredientInOrderData, postOrder } from "../../api/api";
import { useHistory } from "react-router";
import { UserContext } from "../../context/UserContext";

const initialForm: ContactForm = {
  name: {
    elementConfig: {
      type: "text",
      placeholder: "Your Name",
    },
    value: "",
    validation: {
      required: true,
    },
    valid: false,
    touched: false,
  },
  street: {
    elementConfig: {
      type: "text",
      placeholder: "Street",
    },
    value: "",
    validation: {
      required: true,
    },
    valid: false,
    touched: false,
  },
  zipCode: {
    elementConfig: {
      type: "text",
      placeholder: "ZIP Code",
    },
    value: "",
    validation: {
      required: true,
      minLength: 5,
      maxLength: 6,
    },
    valid: false,
    touched: false,
  },
  country: {
    elementConfig: {
      type: "text",
      placeholder: "Country",
    },
    value: "",
    validation: {
      required: true,
    },
    valid: false,
    touched: false,
  },
  email: {
    elementConfig: {
      type: "email",
      placeholder: "Your E-Mail",
    },
    value: "",
    validation: {
      email: true,
      required: true,
    },
    valid: false,
    touched: false,
  },
};

export const checkValidity = (value: string, rules: ValidationRules) => {
  let isValid = true;

  if (rules.required) {
    isValid = value.trim() !== "" && isValid;
  }

  if (rules.minLength) {
    isValid = value.length >= rules.minLength && isValid;
  }

  if (rules.maxLength) {
    isValid = value.length <= rules.maxLength && isValid;
  }

  if (rules.email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    isValid = re.test(value) && isValid;
  }

  return isValid;
};

export const ContactData: React.FC = () => {
  const { ingredients, price, ingredientStore, changeIngredients } = React.useContext(
    IngredientContext
  );
  const { user } = useContext(UserContext);
  const history = useHistory();
  const [form, setForm] = React.useState(initialForm);
  const [formIsValid, toggleFormValidation] = React.useState(false);
  const [isSpinner, toggleSpinner] = React.useState(false);

  const createOrder = () => ({
    price,
    ingredients: Object.entries(ingredients).reduce<IngredientInOrderData[]>(
      (total, [key, count]) => {
        const localArr = [];
        const uid = ingredientStore.find(({ name }) => name === key)?.id;
        if (uid === undefined) return total;
        for (let i = 0; i < count!; ++i) {
          localArr.push({ uid, position: total.length + i + 1 });
        }
        return [...total, ...localArr];
      },
      []
    ),
  });

  const inputChangedHandler = (
    inputIdentifier: string,
    value?: string | null
  ) => {
    const updatedOrderForm = {
      ...form,
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
      formIsValid = updatedOrderForm[inputIdentifier].valid && formIsValid;
    }
    setForm(updatedOrderForm);
    toggleFormValidation(formIsValid);
  };

  const orderHandler = () => {
    if (user.id) {
      toggleSpinner(true);
      postOrder(createOrder(), user.id)
        .then(() => {
          toggleSpinner(false);
          changeIngredients({});
          history.push("/");
        })
        .catch(() => toggleSpinner(false));
    }
  };

  const spinner = isSpinner ? <IonSpinner name="lines" /> : "";

  const formElementsArray = [];
  for (let key in form) {
    formElementsArray.push({
      id: key,
      config: form[key],
    });
  }

  return (
    <div className="ContactData">
      <h4>Enter your Contact Data</h4>
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
          onClick={orderHandler}
          disabled={!formIsValid}
        >
          order&nbsp;{spinner}
        </IonButton>
      </form>
    </div>
  );
};
