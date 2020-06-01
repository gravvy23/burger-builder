export type IngredientType =
  | "meat"
  | "cheese"
  | "bacon"
  | "tomato"
  | "salad"
  | "onion"
  | "cucumber";

export type Ingredients = Partial<Record<IngredientType, number>>;

export type OrderType = {
  id: number;
  price: number;
  ingredients: Ingredients;
};

export type ContactForm = Record<string, FormElement>;

export type ValidationRules = {
  required?: boolean;
  minLength?: number;
  maxLength?: number;
  email?: boolean;
};

export type FormElement = {
  value: string;
  validation: ValidationRules;
  elementConfig: {
    type: "text" | "email" | "password";
    placeholder: string;
  };
  valid: boolean;
  touched: boolean;
  login?: boolean;
};


export type UserType = {
  id?: string;
  name?: string;
  surname?: string;
  email?: string;
}