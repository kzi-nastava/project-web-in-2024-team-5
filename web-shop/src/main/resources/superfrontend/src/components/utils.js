import axios from "axios";
export async function fetchSelf() {
  try {
    const response = await axios.get("http://localhost:8080/api/v1/users/me", {
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (response.status === 200) {
      console.log(response);
      return response.data;
    }
  } catch (error) {
    console.log(error);
    if (error.response && error.response.status === 401) {
    }
  }
}
export async function fetchUser(userId) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/users/${userId}`,
    );
    return response.data;
  } catch (error) {}
}
export function getImageUrl(imagePath) {
  if (imagePath != (`/assets/nike.jpg` || `/assets/monitor.jpg`)) {
    return `/assets/p1.png`;
  }
  return `/assets/${imagePath}`;
}

export async function retrieveMyProducts(products) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/products/user/me`,
    );
    products = response.data;
    return products;
  } catch (error) {
    console.error(error);
  }
}
export async function retrieveProducts(products, page, size) {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/products?page=${page}&size=${size}`,
    );
    products = response.data;
    return products;
  } catch (error) {
    console.error(error);
  }
}
export async function retrieveReceivedReviews() {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/v1/reviews/request/received",
    );
    return response;
  } catch (error) {}
}
export async function retrievePostedReviews() {
  try {
    const response = await axios.get(
      "http://localhost:8080/api/v1/reviews/request/posted",
    );
    return response;
  } catch (error) {}
}
