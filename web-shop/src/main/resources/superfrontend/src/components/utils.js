import axios from "axios";
export function getImageUrl(imagePath) {
  if (imagePath != (`/assets/nike.jpg` || `/assets/monitor.jpg`)) {
    return `/assets/p1.png`;
  }
  return `/assets/${imagePath}`;
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
