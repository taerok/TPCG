<template>
  <v-container>
    <v-form v-model="valid">
      <v-text-field
        v-model="board.title"
        :rules="titleRules"
        label="제목"
        required
      ></v-text-field>
      <v-textarea
        v-model="board.content"
        :rules="contentRules"
        label="내용"
        required
      ></v-textarea>

      <v-btn color="info" class="mr-4" :to="{ name: 'Home' }">
        취소
      </v-btn>
      <v-btn color="primary" @click="postBoard">
        등록
      </v-btn>
    </v-form>
  </v-container>
</template>

<script>
import http from "@/utils/http-common";

export default {
  name: "BoardPost",
  data: () => ({
    valid: false,
    board: {
      title: "",
      content: "",
    },
    titleRules: [
      (v) => !!v || "제목을 입력해주세요",
      (v) => v.length <= 25 || "25글자를 넘을 없습니다.",
    ],
    contentRules: [(v) => !!v || "내용을 입력해주세요"],
  }),
  methods: {
    postBoard() {
      http.post("board/", this.board).then((res) => {
        if (res.data == "success") {
          this.$router.push({ name: "Home" });
        }
      });
    },
  },
};
</script>

<style></style>
