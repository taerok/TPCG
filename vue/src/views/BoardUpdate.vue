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
    <v-btn color="primary" @click="updateBoard">
      수정
    </v-btn>
  </v-form>
  </v-container>
</template>

<script>
import http from "@/utils/http-common";

export default {
  name: "BoardUpdate",
  data: () => ({
    valid: false,
    board: {
      id: "",
      title: "",
      content: "",
    },
    titleRules: [
      (v) => !!v || "제목을 입력해주세요",
      (v) => v.length <= 25 || "25글자를 넘을 없습니다.",
    ],
    contentRules: [(v) => !!v || "내용을 입력해주세요"],
  }),
  created() {
    this.getBoard(this.$route.params.id);
  },
  methods: {
    getBoard(id) {
      http.get("board/" + id).then((res) => {
        this.board = res.data;
      });
    },
    updateBoard() {
      http.patch("board/", this.board).then((res) => {
        if (res.data == "success") {
          this.$router.push({ name: "Home" });
        }
      });
    },
  },
};
</script>

<style></style>
