<template>
    <div class="w-full">
        <div v-if="loading" class="text-center">Loading reports...</div>
        <div v-else class="grid grid-cols-4 gap-4">
            <ReportCard v-for="report in reports" :key="report.id" :report="report" @report-updated="fetchReports" />
        </div>
    </div>
</template>

<script>

import ReportCard from "@/components/ReportCardComp.vue"
import axios from 'axios';

export default {
    name: "ReportsGrid",
    components: {
        ReportCard,
    },
    data() {
        return {
            reports: [],
        };
    },
    mounted() {
        this.fetchReports();
    },
    methods: {
        async fetchReports() {
            try {
                const res = await axios.get("http://localhost:8080/api/v1/reports");
                this.reports = res.data;
            } catch (error) {
                console.error("Error fetching reports:", error);
            }
        },
    },
}

</script>