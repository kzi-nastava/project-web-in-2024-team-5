<template>
  <div>
    <div
      class="m-0 p-0 w-80 pl-14 fixed h-full border-r border- border-spacing-32 border-slate-200"
    >
      <div id="textcontainer">
        <div
          class="flex flex-col items-center align-middle overflow-auto font-sans font-light"
        >
          <h2 class="text-2xl leading-tight mt-10">Kategorije</h2>
          <p>Automobili</p>
          <p>Odeca</p>
          <p>Obuca</p>
          <p>Elektronika</p>
          <p>Alati</p>
        </div>
        <div
          class="flex flex-col items-center align-middle overflow-auto font-sans font-light h-40"
        >
          <h2 class="text-2xl leading-normal mt-10">Cena</h2>
          <input type="range" min="0" max="10000000" />
        </div>
      </div>
      <br />
    </div>
  </div>
  <RouterView />
</template>
<style scoped></style>

<script></script>

