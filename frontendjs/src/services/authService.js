import axios from "axios";
export async function signin() {
    const response = await axios.get(`http://localhost:8084/login/password`);
    console.log(response);
  }