<template>
  <section class="py-12 font-[Open_Sans]">
    <div class="container w-[1200px] mx-auto">
      <div class="w-full bg-gray-700 h-[1px] mb-12"></div>
      <div class="flex justify-between">
        <div class="my-auto">
          <div class="h-full flex flex-col">
            <h2 class="text-xl pb-9">Super shop</h2>
            <div class="flex gap-3">
              <img
                class="max-w-5"
                src="../assets/hashtag-square-svgrepo-com.svg"
                alt=" Social media icon"
              />
              <img
                class="max-w-5"
                src="../assets/hashtag-square-svgrepo-com.svg"
                alt=" Social media icon"
              />
              <img
                class="max-w-5"
                src="../assets/hashtag-square-svgrepo-com.svg"
                alt=" Social media icon"
              />
              <img
                class="max-w-5"
                src="../assets/hashtag-square-svgrepo-com.svg"
                alt=" Social media icon"
              />
            </div>
          </div>
        </div>

        <div class="flex gap-40">
          <div class="flex flex-col gap-5">
            <h4 class="font-bold">Topic</h4>
            <a href="">Page</a>
            <a href="">Page</a>
            <a href="">Page</a>
            <a href="">Page</a>
          </div>
          <div class="flex flex-col gap-5">
            <h4 class="font-bold">Topic</h4>
            <router-link to="/home">Home</router-link>
            <a href="">Page</a>
            <a href="">Page</a>
            <a href="">Page</a>
            <a href="">Page</a>
          </div>
          <div class="flex flex-col gap-5">
            <h4 class="font-bold">Topic</h4>
            <a href="">Page</a>
            <a href="">Page</a>
            <a href="">Page</a>
            <a href="">Page</a>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "FooterComp",
};
</script>
