<template>
  <div class="py-4 px-3 shadow-lg rounded-lg">
    <div>
      <p>Ocenjeni: {{ ocenjeni.name }} {{ ocenjeni.lastname }}</p>
      <br />
      <p class="mb-12">Postavio: {{ postavio.name }} {{ postavio.lastname }}</p>
      <p class="mb-3">{{ review.comment }}</p>
      <p class="mb-12">Ocena: {{ review.score }}</p>
    </div>
    <div>
    <label for="newComment">Izmena recenzije:</label>
    <textarea v-model="rejectReason" rows="3" placeholder="Izmenjena recenzija..." class="w-full mb-2 border border-black"></textarea>
    <div class="w-full flex justify-between">
      <button @click="deleteReview" class="reject text-red-600">Obrisi</button>
      <button @click="izmeniReview" class="accept text-green-700">Izmeni</button>
    </div>
</div>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  name: "ReviewCard",
  data() {
    return {
      ocenjeni: {
        type: Object,
      },
      postavio: {
        type: Object,
      },
      rejectReason: "",
    }
  },
  props: {
    review: {
      type: Object,
      required: true,
    },
  },
  mounted() {
    axios
      .get("http://localhost:8080/api/v1/users/" + this.review.reviewedUserId)
      .then((res) => {
        this.ocenjeni = res.data;
      })
      .catch((err) => {
        console.log(err)
      });

    axios
      .get("http://localhost:8080/api/v1/users/" + this.review.reviewingUserId)
      .then((res) => {
        this.postavio = res.data;
      })
      .catch((err) => {
        console.log(err)
      });
  },
  methods: {
    async deleteReview() {
      const res = await axios.delete("http://localhost:8080/api/v1/reviews/" + this.review.id)
      this.$emit('review-updated');
    },
    async izmeniReview() {
      const res = await axios.patch("http://localhost:8080/api/v1/reviews/" + this.review.id, {
        comment: this.rejectReason,
      },)
      this.rejectReason = ""
      this.$emit('review-updated');
    },
  },
};
</script>
