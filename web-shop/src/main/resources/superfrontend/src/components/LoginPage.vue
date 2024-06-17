<template>
    <div class = "w-[1200px] flex flex-col items-center m-auto">
        <div>
            <form @submit.prevent="login">
                <input class ="border-[2px] border-black rounded-xl p-1 " v-model="user.username" type="text" name="user.username" required="true" placeholder="Unesi korisnicko ime">
                <br/>
                <br/>
                <input class ="border-[2px] border-black rounded-xl p-1" v-model="user.password" type="password" name="user.password" required="true" placeholder="Unesi lozinku">
            </form>
            <button class ="mt-10 rounded-lg bg-[#004E9D] text-white w-[225px] h-[40px] drop-shadow-lg" @click="login(), $emit('render')">Login</button>
        </div>  
    </div>
</template>

<script>
import axios from 'axios';
export default {
    data() {
        return {
            user: {
                username: '',
                password: ''
            }
        }
    },
    methods: {
        close() {

        },      
        async login() {
            try {
                const response = await axios.post("http://localhost:8080/api/v1/login", this.user, {
                    withCredentials: true,
                    headers: {
                        "Content-Type": 'application/json'
                    }
                });
                if (response.status === 200) {
                    this.$forceUpdate();
                    this.$router.push('/');
                    alert("Successfully logged in!")
                } else if (response.status === 401) {
                    alert("User doesn't exist!")
                }
            }
            catch (error) {
                if(error.response && error.response.status === 401) {
                alert("User doesn't exist!")
                }
                console.error(error);
            }

        }
    }
}
</script>

<style></style>