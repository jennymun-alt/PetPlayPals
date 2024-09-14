<template></template>

<script>
import AuthService from "../service/AuthService";
export default {
  data() {
    return {
      user: [],
    };
  },

  methods: {},
  created() {
    AuthService.list().then((response) => {
      this.user = response.data;
    });
  },
};
</script>

<style>
</style>