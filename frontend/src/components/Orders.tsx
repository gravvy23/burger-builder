import React from "react";
import { Order } from "./Order/Order";
import { getOrders, OrderStore } from "../api/api";
import { OrderType, Ingredients, IngredientType } from "../types";
import { IonLoading } from "@ionic/react";
import { UserContext } from "../context/UserContext";

const mapAPIDataToOrders = (data: OrderStore) =>
  data.map<OrderType>((item) => {
    const ingredients = item.ingredients.reduce<Ingredients>(
      (obj, ingredient) => {
        const type = ingredient.ingredient.name as IngredientType;
        if (obj[type] !== undefined) {
          obj[type]!++;
        } else {
          obj[type] = 1;
        }
        return obj;
      },
      {}
    );
    return {
      id: item.id,
      price: item.price,
      ingredients,
    };
  });

export const Orders: React.FC = () => {
  const { user } = React.useContext(UserContext);
  const [orders, setOrders] = React.useState<OrderType[]>([]);
  const [loading, toggleLoading] = React.useState(false);

  React.useEffect(() => {
    const getAndSaveOrders = async () => {
      if (user.id) {
        toggleLoading(true);
        const orders = await getOrders(user.id);
        toggleLoading(false);
        setOrders(mapAPIDataToOrders(orders));
      }
    };
    getAndSaveOrders();
  }, []);
  const data = !loading ? (
    orders.map((order) => <Order key={order.id} order={order} />)
  ) : (
    <IonLoading
        isOpen={loading}
        message={'Loading orders...'}
      />
  );
  return <div className="Orders">{data}</div>;
};
