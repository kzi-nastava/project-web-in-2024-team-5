<template>
    <header>
        <div class ="flex flex-col justify-items-center">
            <div class = " bg-slate-300  flex justify-center gap-10 p-3" >
                <RouterLink to = "/">SuperWebShop</RouterLink>
                <div>
                    <input class = "rounded-full flex justify-center w-80" type= "text">
                </div>
                    <RouterLink to = "/logout">Logout</RouterLink>
                    <RouterLink to = "/login">Login</RouterLink>
                    <RouterLink to = "/register">Register</RouterLink>
            </div>
            <div class ="bg-slate-200 flex flex-row justify-evenly gap-48">
                <RouterLink to = "/products">Products</RouterLink>
                <RouterLink to = "/about">About us</RouterLink>
            </div>
        </div>
    </header>
</template>

<script>

</script>

