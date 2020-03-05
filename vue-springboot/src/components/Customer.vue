<template>
  <div v-if="this.customer">
    <h4>Customer</h4>
    <div>
      <label>Name: </label> {{this.customer.name}}
    </div>
    <div>
      <label>Street Name: </label> {{this.customer.streetName}}
    </div>
    <div>
      <label>Street Number: </label> {{this.customer.streetNumber}}
    </div>
    <div>
      <label>Phone Number: </label> {{this.customer.phoneNumber}}
    </div>
    <div>
      <label>E-Mail: </label> {{this.customer.email}}
    </div>
  
    
  
    <span class="button is-small btn-danger" v-on:click="deleteCustomer()">Delete</span>
  </div>

  <div v-else>
    <br/>
    <p>Please click on a Customer...</p>
  </div>
</template>

<script>
import http from "../http-common";

export default {
  name: "customer",
  props: ["customer"],
  methods: {
   
    deleteCustomer() {
      http
        .delete("/customer/" + this.customer.id)
        .then(response => {
          console.log(response.data);
          this.$emit("refreshData");
          this.$router.push('/');
        })
        .catch(e => {
          console.log(e);
        });
    }
    /* eslint-enable no-console */
  }
};
</script>