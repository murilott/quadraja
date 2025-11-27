import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider, createBrowserRouter } from "react-router-dom";
import { Authentication } from "./pages/Authentication/Authentication";
import { Navbar } from "./components/Navbar/Navbar";
import { Home } from "./pages/Home/Home";
import { GlobalStyled } from "./GlobalStyled";
import { Pagamento } from "./pages/Pagamento/Pagamento";
import { Reservas } from "./pages/Reservas/Reservas";
import { Usuarios } from "./pages/Usuarios/Usuarios";

const router = createBrowserRouter([
  {
    path: "/home",
    element: <Navbar />,
    children: [
      {
        path: "/home",
        element: <Home />,
      },
      {
        path: "/home/pagamento",
        element: <Pagamento />,
      },
      {
        path: "/home/reservas",
        element: <Reservas />,
      },
      {
        path: "/home/usuarios",
        element: <Usuarios />,
      },
    ],
  },
  {
    path: "/",
    element: <Authentication />,
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <GlobalStyled />
    {/* <SkeletonTheme baseColor="#3b3b3b" highlightColor="#807DF0" duration={0.5}> */}
      {/* <UserProvider> */}
        <RouterProvider router={router} />
      {/* </UserProvider> */}
    {/* </SkeletonTheme> */}
  </React.StrictMode>
);