<template>
  <Transition name="modal">
    <div v-if="show" class="modal-mask">
      <div
        class="drop-shadow-lg bg-white p-12 font-[Open_Sans] text-3xl rounded-2xl"
      >
        <div class="modal-header">
          <slot name="header">Default Header</slot>
        </div>
        <div class="modal-body">
          <slot></slot>
        </div>
        <div class="modal-footer">
          <slot name="footer">
            <button @click="$emit('close')">Close</button>
          </slot>
        </div>
      </div>
    </div>
  </Transition>
</template>

<script>
export default {
  props: {
    show: {
      type: Boolean,
      required: true,
    },
  },
};
</script>

<style scoped>
.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}
.modal-container {
  background: white;
  padding: 20px;
  border-radius: 5px;
  width: 300px;
  max-width: 90%;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
.modal-header,
.modal-footer {
  padding: 10px;
  border-bottom: 1px solid #e8e8e8;
}
.modal-footer {
  border-top: 1px solid #e8e8e8;
  border-bottom: none;
  text-align: right;
}
</style>
