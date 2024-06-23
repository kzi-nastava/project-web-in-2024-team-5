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

    <div class="flex flex-col justify-end">
      <div @mouseover="this.hover = true" @mouseleave="this.hover = false">
        <img src="/public/assets/strelica.svg" />
      </div>
      <div
        v-if="hover"
        @mouseover="this.hover = true"
        @mouseleave="this.hover = false"
      >
        <RouterLink to="/logout">Izloguj se</RouterLink>
        <br />
        <RouterLink to="/settings">Podešavanja</RouterLink>
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
  name: "ProfileIcon",
  props: {
    user: {
      type: Object,
      required: true,
    },
  },
  methods() {
    function hoverTrue() {
      this.hover = true;
    }
  },
};
</script>
