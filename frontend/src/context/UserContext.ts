import React from "react";
import { UserType } from "../types";

type UserContext = {
  user: UserType;
  changeUser: React.Dispatch<React.SetStateAction<UserType>>;
};
export const UserContext = React.createContext<UserContext>({
  user: {},
  changeUser: () => {},
});
