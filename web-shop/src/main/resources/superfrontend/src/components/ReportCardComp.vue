<template>
    <div class="py-4 px-3 shadow-lg rounded-lg">
        <div>
            <p>Prijavljen: {{ prijavljeni.name }} {{ prijavljeni.lastname }}</p>
            <br />
            <p class="mb-12">Postavio: {{ postavio.name }} {{ postavio.lastname }}</p>
            <p class="mb-3">Status: {{ report.status }}</p>
            <p class="mb-12">{{ report.reason }}</p>
        </div>
        <div v-if="!loading && report.status === 'SUBMITTED'">
            <label for="newComment">Razlog odobravanja:</label>
            <textarea v-model="acceptReason" rows="3" placeholder="Razlog odobravanja prijave..."
                class="w-full mb-2 border border-black"></textarea>
            <div class="w-full flex justify-between">
                <button @click="rejectReport" class="reject text-red-600">Reject</button>
                <button @click="acceptReport" class="accept text-green-700">Accept</button>
            </div>
        </div>
        <div v-if="loading" class="loading-indicator">
            Loading...
        </div>
    </div>
</template>

<script>

import axios from 'axios';

export default {
    name: "ReportCard",
    data() {
        return {
            prijavljeni: {
                type: Object,
            },
            postavio: {
                type: Object,
            },
            acceptReason: "",
            loading: false,
        }
    },
    props: {
        report: {
            type: Object,
            required: true,
        },
    },
    mounted() {
        axios
            .get("http://localhost:8080/api/v1/users/" + this.report.whoIsReported)
            .then((res) => {
                this.prijavljeni = res.data;
            })
            .catch((err) => {
                console.log(err)
            });

        axios
            .get("http://localhost:8080/api/v1/users/" + this.report.whoReported)
            .then((res) => {
                this.postavio = res.data;
            })
            .catch((err) => {
                console.log(err)
            });
    },
    methods: {
        async rejectReport() {
            this.loading = true;
            const res = axios.post("http://localhost:8080/api/v1/reports/resolve/" + this.report.id, {
                resolution: "rejected",
            }).then((res) => {
                this.loading = false;
                this.$emit('report-updated');
            }).catch((err) => {
                console.log(err)
            })

        },
        async acceptReport() {
            this.loading = true;
            try {
                const res = await axios.post("http://localhost:8080/api/v1/reports/resolve/" + this.report.id, {
                    reason: this.acceptReason,
                    resolution: "accepted",
                });
                this.loading = false;
                this.acceptReason = "";
                this.$emit('report-updated');
            } catch (error) {
                console.log(error);
            } finally {
                this.loading = false;
            }
        },
    },
};
</script>

<style>
.loading-indicator {
    margin-top: 1rem;
    text-align: center;
    font-weight: bold;
    color: #666;
}
</style>