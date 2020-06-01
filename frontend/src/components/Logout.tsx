import React from 'react';
import { UserContext } from '../context/UserContext';
import { Redirect } from 'react-router';

export const Logout: React.FC = () => {
    const { changeUser } = React.useContext(UserContext);

    React.useEffect(() => {
        localStorage.removeItem('usrid');
        changeUser({});
    }, []);

    return (
        <Redirect to="/page/Burger" />
    )
}