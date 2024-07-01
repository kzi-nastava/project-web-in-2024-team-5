<template>
  <div class="flex flex-row gap-4 items-center font-[Open_Sans]">
    <img src="/public/assets/icon.svg" />
    <RouterLink
      :to="{
        name: 'me',
      }"
      class="text-2xl"
      >{{ user.name }}</RouterLink
    >

    <div class="relative flex flex-col justify-end">
      <div @mouseover="hover = true" @mouseleave="hover = false">
        <img src="/public/assets/strelica.svg" />
      </div>
      <div
        v-if="hover"
        @mouseover="hover = true"
        @mouseleave="hover = false"
        class="absolute z-10 top-5 bg-white p-4 rounded-lg shadow-lg border transition-opacity duration-300 ease-in-out"
        :class="{ 'opacity-100': hover, 'opacity-0': !hover }"
      >
        <RouterLink
          to="/logout"
          class="block p-2 text-[#004E9D] text-lg hover:bg-gray-100 rounded-md transition-colors duration-300 ease-in-out"
        >
          Izloguj se
        </RouterLink>
        <RouterLink
          to="/settings"
          class="block p-2 text-[#004E9D] text-lg hover:bg-gray-100 rounded-md transition-colors duration-300 ease-in-out"
        >
          Podešavanja
        </RouterLink>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      hover: false,
    };
  },
  props: {
    user: {
      type: Object,
      required: true,
    },
  },
  methods() {},
};
</script>
