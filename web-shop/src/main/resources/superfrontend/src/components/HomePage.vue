<template>
  <section
    class="ml-0 mr-0 pt-3 text-center relative text-white font-['Open_Sans']"
  >
    <div class="brightness-[.2]">
      <img src="/src/assets/bkgr.jpeg" />
    </div>
    <div class="container w-[1200px]">
      <h1 class="absolute top-[83px] text-5xl left-[18.75%] font-extrabold">
        Big Hero Title
      </h1>
      <p class="absolute top-[183px] text-xl left-[18.75%] w-96 text-left">
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat.
      </p>
      <div class="flex flex-row justify-center items-center gap-10">
        <RouterLink to="/register">
          <button
            class="absolute top-[383px] left-[18.75%] drop-shadow-xl rounded-xl w-[105px] h-[41px] bg-[#B84E15]"
          >
            Register
          </button>
        </RouterLink>
        <RouterLink to="/login">
          <button
            class="absolute top-[390px] left-[25%] drop-shadow-xl rounded-xl w-[76px] h-[27px] bg-white text-s text-black"
          >
            Login
          </button>
        </RouterLink>
      </div>
    </div>
  </section>
  <section>
    <div
      class="container w-[1200px] flex flex-col ml-auto mr-auto pt-[80px] pb-[80px] bg-[#FBFBF9]"
    >
      <div class="font-[Open_Sans] text-[#011839]">
        <h2 class="font-bold text-4xl">Izdvojeni proizvodi</h2>
        <br />
        <p class="text-xl">Ove nedelje izdvajamo ove proizvode</p>
        <br />
      </div>
      <div class="container">
        <div class="grid grid-cols-4" id="products">
          <div
            v-for="product in products"
            :key="product.id"
            class="item flex flex-col"
          >
            <router-link
              :to="{
                name: 'product',
                params: { id: product.id },
                query: {
                  img: product.imagePath,
                  name: product.name,
                  price: product.price,
                },
              }"
            >
              <div
                class="flex flex-col justify-center items-center bg-white h-[295px] w-[265px] rounded-2xl drop-shadow-lg font-[Open_Sans]"
              >
                <img
                  :src="getImageUrl(product.imagePath)"
                  alt="Product Image"
                  class="bg-transparent pb-2 self-center w-1/2"
                />

                <h2 class="font-semibold text-lg" itemprop="name">
                  {{ product.name }}
                </h2>
                <br />
                <h3
                  class="absolute bottom-4 left-[10%] text-orange-800"
                  itemprop="price"
                >
                  ${{ product.price }}
                </h3>
              </div>
            </router-link>
          </div>
          <RouterLink to="/products">Pogledajte sve proizvode </RouterLink>
        </div>
      </div>
    </div>
  </section>
  <section class="bg-[#B84E15] h-[400px]">
    <div
      class="container flex flex-row items-center mx-auto w-[1200px] h-[400px] font-[Open_Sans]"
    >
      <div>
        <h2 class="text-5xl text-white font-bold">Preporučene kategorije</h2>
        <br />
        <div class="text-2xl text-white max-w-[350px]">
          <p>Imamo mnogo različitih proizvoda.</p>
          <p>Pogledajte neke od proizvoda iz izdvojenih kategorija.</p>
        </div>
      </div>
      <div class="flex flex-col gap-8 pl-64">
        <div
          class="flex flex-row justify-start gap-2 p-3 bg-white w-[276px] items-center h-[60px] rounded-xl drop-shadow-lg text-xl"
        >
          <svg
            width="40"
            height="38"
            viewBox="0 0 40 38"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M8 0.799988H32C34.2092 0.799988 36 2.59119 36 4.79999V22.8C36 25.0084 34.2092 26.8 32 26.8H8C5.7912 26.8 4 25.0084 4 22.8V4.79999C4 2.59119 5.7912 0.799988 8 0.799988Z"
              fill="#EBEDEE"
            />
            <path
              d="M5.6 37.2H6.4L10.4 33.2H5.6V37.2ZM29.6 33.2L33.6 37.2H34.4V33.2H29.6Z"
              fill="#212E3C"
            />
            <path
              d="M20 10C19.6817 10 19.3765 10.1264 19.1515 10.3515C18.9264 10.5765 18.8 10.8817 18.8 11.2C18.8 11.5183 18.9264 11.8235 19.1515 12.0485C19.3765 12.2736 19.6817 12.4 20 12.4C20.3183 12.4 20.6235 12.2736 20.8485 12.0485C21.0736 11.8235 21.2 11.5183 21.2 11.2C21.2 10.8817 21.0736 10.5765 20.8485 10.3515C20.6235 10.1264 20.3183 10 20 10ZM13.6 6C13.2817 6 12.9765 6.12643 12.7515 6.35147C12.5264 6.57652 12.4 6.88174 12.4 7.2C12.4 7.51826 12.5264 7.82348 12.7515 8.04853C12.9765 8.27357 13.2817 8.4 13.6 8.4C13.9183 8.4 14.2235 8.27357 14.4485 8.04853C14.6736 7.82348 14.8 7.51826 14.8 7.2C14.8 6.88174 14.6736 6.57652 14.4485 6.35147C14.2235 6.12643 13.9183 6 13.6 6ZM26.4 8.4C26.7183 8.4 27.0235 8.27357 27.2485 8.04853C27.4736 7.82348 27.6 7.51826 27.6 7.2C27.6 6.88174 27.4736 6.57652 27.2485 6.35147C27.0235 6.12643 26.7183 6 26.4 6C26.0817 6 25.7765 6.12643 25.5515 6.35147C25.3264 6.57652 25.2 6.88174 25.2 7.2C25.2 7.51826 25.3264 7.82348 25.5515 8.04853C25.7765 8.27357 26.0817 8.4 26.4 8.4ZM13.6 14C13.2817 14 12.9765 14.1264 12.7515 14.3515C12.5264 14.5765 12.4 14.8817 12.4 15.2C12.4 15.5183 12.5264 15.8235 12.7515 16.0485C12.9765 16.2736 13.2817 16.4 13.6 16.4C13.9183 16.4 14.2235 16.2736 14.4485 16.0485C14.6736 15.8235 14.8 15.5183 14.8 15.2C14.8 14.8817 14.6736 14.5765 14.4485 14.3515C14.2235 14.1264 13.9183 14 13.6 14ZM26.4 14C26.0817 14 25.7765 14.1264 25.5515 14.3515C25.3264 14.5765 25.2 14.8817 25.2 15.2C25.2 15.5183 25.3264 15.8235 25.5515 16.0485C25.7765 16.2736 26.0817 16.4 26.4 16.4C26.7183 16.4 27.0235 16.2736 27.2485 16.0485C27.4736 15.8235 27.6 15.5183 27.6 15.2C27.6 14.8817 27.4736 14.5765 27.2485 14.3515C27.0235 14.1264 26.7183 14 26.4 14Z"
              fill="#BDC3C7"
            />
            <path d="M4 26.4H36V33.2H4V26.4Z" fill="#283848" />
            <path
              d="M36 15.2C38.2092 15.2 40 16.9916 40 19.2V29.2C40 31.4092 38.2092 33.2 36 33.2C33.7908 33.2 32 31.4092 32 29.2V19.2C32 16.9916 33.7908 15.2 36 15.2Z"
              fill="#2C3E50"
            />
            <path
              d="M36 17.2C37.3248 17.2 38.4 18.2752 38.4 19.6V28.8C38.4 29.4365 38.1471 30.047 37.6971 30.4971C37.247 30.9472 36.6365 31.2 36 31.2C35.3635 31.2 34.753 30.9472 34.3029 30.4971C33.8529 30.047 33.6 29.4365 33.6 28.8V19.6C33.6 18.2752 34.6752 17.2 36 17.2Z"
              fill="#34495E"
            />
            <path
              d="M4 15.2C6.2088 15.2 8 16.9916 8 19.2V29.2C8 31.4092 6.2088 33.2 4 33.2C1.7912 33.2 0 31.4092 0 29.2V19.2C0 16.9916 1.7912 15.2 4 15.2Z"
              fill="#2C3E50"
            />
            <path
              d="M4 17.2C5.3252 17.2 6.4 18.2752 6.4 19.6V28.8C6.4 29.4365 6.14714 30.047 5.69705 30.4971C5.24697 30.9472 4.63652 31.2 4 31.2C3.36348 31.2 2.75303 30.9472 2.30294 30.4971C1.85285 30.047 1.6 29.4365 1.6 28.8V19.6C1.6 18.2752 2.6748 17.2 4 17.2Z"
              fill="#34495E"
            />
            <path d="M8 26.4H32V27.6H8V26.4Z" fill="#253241" />
            <path
              d="M8.2 21.2H31.8C32.4896 21.2 33.1509 21.4739 33.6385 21.9615C34.1261 22.4491 34.4 23.1104 34.4 23.8C34.4 24.4896 34.1261 25.1509 33.6385 25.6385C33.1509 26.1261 32.4896 26.4 31.8 26.4H8.2C7.51044 26.4 6.84912 26.1261 6.36152 25.6385C5.87393 25.1509 5.6 24.4896 5.6 23.8C5.6 23.1104 5.87393 22.4491 6.36152 21.9615C6.84912 21.4739 7.51044 21.2 8.2 21.2Z"
              fill="#BDC3C7"
            />
          </svg>
          Nameštaj
        </div>
        <div
          class="flex flex-row justify-start gap-2 p-3 bg-white w-[276px] items-center h-[60px] rounded-xl drop-shadow-lg text-xl"
        >
          <svg
            width="40"
            height="40"
            viewBox="0 0 40 40"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <g clip-path="url(#clip0_12_31)">
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M2.4 40H37.6C38.2365 40 38.847 39.7471 39.2971 39.2971C39.7471 38.847 40 38.2365 40 37.6V2.4C40 1.76348 39.7471 1.15303 39.2971 0.702944C38.847 0.252856 38.2365 0 37.6 0L2.4 0C1.76348 0 1.15303 0.252856 0.702944 0.702944C0.252856 1.15303 0 1.76348 0 2.4L0 37.6C0 38.2365 0.252856 38.847 0.702944 39.2971C1.15303 39.7471 1.76348 40 2.4 40Z"
                fill="#C0392B"
              />
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M40 12H39.99C39.9401 11.4536 39.6878 10.9455 39.2827 10.5755C38.8775 10.2055 38.3487 10.0002 37.8 10H2.5C1.1748 10 0 8.9256 0 7.6V2.4C0 1.76348 0.252856 1.15303 0.702944 0.702944C1.15303 0.252856 1.76348 0 2.4 0L37.6 0C38.9252 0 40 1.0748 40 2.4004V12Z"
                fill="#E74C3C"
              />
              <path
                opacity="0.15"
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M2 40H2.8V0H2V40Z"
                fill="black"
              />
              <path
                opacity="0.15"
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M2.8 40H3.6V0H2.8V40Z"
                fill="white"
              />
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M40 4.00043C40 3.46999 39.7893 2.96129 39.4142 2.58621C39.0391 2.21114 38.5304 2.00043 38 2.00043H2.8C2.58783 2.00043 2.38434 2.08471 2.23431 2.23474C2.08429 2.38477 2 2.58825 2 2.80043V7.20043C2 7.4126 2.08429 7.61608 2.23431 7.76611C2.38434 7.91614 2.58783 8.00043 2.8 8.00043H37.6C38.2365 8.00043 38.847 8.25328 39.2971 8.70337C39.7471 9.15346 40 9.76391 40 10.4004V4.40043H39.9596C39.986 4.27123 40 4.13723 40 4.00043Z"
                fill="white"
              />
              <path
                opacity="0.2"
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M40 6.16919V4.60079C40 4.60079 40.0732 3.00079 38 3.00079H2V3.80079H37.6C38.2095 3.8011 38.7961 4.03351 39.2404 4.45079C39.6847 4.86806 39.9535 5.43887 39.992 6.04719C39.9492 5.64719 39.6764 4.60119 38 4.60119H2V5.40119H37.6C38.2095 5.4015 38.7961 5.63391 39.2404 6.05119C39.6847 6.46846 39.9535 7.03927 39.992 7.64759C39.9492 7.24719 39.6764 6.20119 38 6.20119H2V7.00119H37.6C38.2365 7.00119 38.847 7.25405 39.2971 7.70414C39.7471 8.15422 40 8.76467 40 9.40119V6.16919Z"
                fill="#95A5A6"
              />
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M32 40H36V10H32V40Z"
                fill="#2980B9"
              />
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M32 10.0028H36V0H32V10.0028Z"
                fill="#3498DB"
              />
              <path
                opacity="0.1"
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M32.8 40H33.6V0H32.8V40ZM34.4 0V40H35.2V0H34.4Z"
                fill="black"
              />
            </g>
            <defs>
              <clipPath id="clip0_12_31">
                <rect width="40" height="40" fill="white" />
              </clipPath>
            </defs>
          </svg>
          Knjige
        </div>
        <div
          class="flex flex-row justify-start gap-2 p-3 bg-white w-[276px] items-center h-[60px] rounded-xl drop-shadow-lg text-xl"
        >
          <svg
            width="40"
            height="38"
            viewBox="0 0 40 38"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              clip-rule="evenodd"
              d="M38 31.6H24.8V36.4H26.6C26.7591 36.4 26.9117 36.4632 27.0243 36.5757C27.1368 36.6883 27.2 36.8409 27.2 37C27.2 37.1591 27.1368 37.3117 27.0243 37.4243C26.9117 37.5368 26.7591 37.6 26.6 37.6H13.4C13.2409 37.6 13.0883 37.5368 12.9757 37.4243C12.8632 37.3117 12.8 37.1591 12.8 37C12.8 36.8409 12.8632 36.6883 12.9757 36.5757C13.0883 36.4632 13.2409 36.4 13.4 36.4H15.2V31.6H2C1.46957 31.6 0.960859 31.3893 0.585786 31.0142C0.210714 30.6391 0 30.1304 0 29.6L0 26H40V29.6C40 30.1304 39.7893 30.6391 39.4142 31.0142C39.0391 31.3893 38.5304 31.6 38 31.6Z"
              fill="#BDC3C7"
            />
            <path
              fill-rule="evenodd"
              clip-rule="evenodd"
              d="M15.2 31.6H24.8V32.4H15.2V31.6Z"
              fill="#95A5A5"
            />
            <path
              fill-rule="evenodd"
              clip-rule="evenodd"
              d="M2 0H38C38.5304 0 39.0391 0.210714 39.4142 0.585786C39.7893 0.960859 40 1.46957 40 2V26H0V2C0 1.46957 0.210714 0.960859 0.585786 0.585786C0.960859 0.210714 1.46957 0 2 0Z"
              fill="#2C3E50"
            />
            <path
              fill-rule="evenodd"
              clip-rule="evenodd"
              d="M2 2H38V24H2V2Z"
              fill="#35495E"
            />
            <path
              d="M20 29.6C20.4418 29.6 20.8 29.2418 20.8 28.8C20.8 28.3582 20.4418 28 20 28C19.5582 28 19.2 28.3582 19.2 28.8C19.2 29.2418 19.5582 29.6 20 29.6Z"
              fill="#35495E"
            />
          </svg>
          Tehnologija
        </div>
      </div>
    </div>
  </section>
</template>
<style scoped></style>

<script>
import axios from "axios";
import { getImageUrl } from "./utils.js";
import { retrieveProducts } from "./utils.js";
export default {
  data() {
    return {
      products: [],
      getImageUrl,
      retrieveProducts,
    };
  },
  methods: {
    async fetchProducts() {
      const response = await retrieveProducts(this.products, 1, 4);
      this.products = response;
    },
  },
  created() {
    this.fetchProducts();
  },
};
</script>
