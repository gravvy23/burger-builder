import axios from "axios";
import { IngredientType, UserType } from "../types";
import { TOKEN } from "./token";

export type FirebaseUserData = {
  email: string;
  expiresIn: string;
  idToken: string;
  kind: string;
  localId: string;
  refreshToken: string;
};

export type IngredientInOrderData = {
  uid: number;
  position: number;
};

export type GetIngredientOrderData = IngredientInOrderData & {
  id: number;
  ingredient: IngredientsData;
};

export type IngredientsData = {
  id: number;
  price: number;
  displayname: string;
  name: IngredientType;
};

export type OrderData = {
  price: number;
  ingredients: Array<IngredientInOrderData>;
};

export type GetOrderData = {
  id: number;
  price: number;
  ingredients: Array<GetIngredientOrderData>;
};

type Pageable<T> = {
  content: T;
};

export type OrderStore = Array<GetOrderData>;
export type IngredientStore = Array<IngredientsData>;

export const getIngredients = () =>
  axios
    .get<Pageable<IngredientStore>>("/api/ingredients?sort=id")
    .then(({ data }) => data.content);

export const getOrders = (userId: string) =>
  axios
    .get<Pageable<OrderStore>>(`/api/users/${userId}/orders`)
    .then(({ data }) => data.content);

export const postOrder = (order: OrderData, userId: string) =>
  axios.post(`api/users/${userId}/orders`, order);

export const createUser = (
  email: string,
  password: string,
  name: string,
  surname: string
) => {
  const authData = {
    email,
    password,
    returnSecureToken: true,
  };
  return axios
    .post<FirebaseUserData>(
      `https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=${TOKEN}`,
      authData
    )
    .then((response) => response.data)
    .then((data) => {
      const userInLocalApi = {
        id: data.localId,
        name,
        surname,
        mail: email,
      };
      return axios.post<UserType>("api/users", userInLocalApi);
    })
    .then((response) => {
      response.data.id && localStorage.setItem("usrid", response.data.id);
      return response.data;
    });
};

export const signIn = (email: string, password: string) => {
  const authData = {
    email,
    password,
    returnSecureToken: true,
  };
  return axios
    .post<FirebaseUserData>(
      `https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${TOKEN}`,
      authData
    )
    .then((response) => response.data)
    .then((data) => axios.get<UserType>("/api/users/" + data.localId))
    .then((response) => {
      response.data.id && localStorage.setItem("usrid", response.data.id);
      return response.data;
    });
};

export const getUser = (userId: string) =>
  axios.get<UserType>("/api/users/" + userId).then((response) => response.data);
