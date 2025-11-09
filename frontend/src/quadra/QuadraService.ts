import axios from "axios";
import type { QuadraType } from "./QuadraType";

// const BASE_URL = import.meta.env.VITE_BACKEND_URL;
const BASE_URL = 'https://accursed-phantasm-5j9xqj59jqr2r6j-8082.app.github.dev/quadras';

export const listQuadras = async (): Promise<QuadraType[]> => {
  const response = await axios.get<QuadraType[]>(`${BASE_URL}`);
  return response.data;
};