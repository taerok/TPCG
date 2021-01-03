<template>
  <v-card>
    <v-toolbar flat>
      <v-btn
        outlined
        @click="movePostBoardPage"
      >
        등록
      </v-btn>
      
      <v-spacer></v-spacer>
      <v-menu
        bottom
        right
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            outlined
            v-bind="attrs"
            v-on="on"
          >
            <span>{{ optionToLabel[option] }}</span>
            <v-icon right>
              mdi-menu-down
            </v-icon>
          </v-btn>
        </template>
        <v-list>
          <v-list-item @click="option = 'all'">
            <v-list-item-title>전체</v-list-item-title>
          </v-list-item>
          <v-list-item @click="option = 'title'">
            <v-list-item-title>제목</v-list-item-title>
          </v-list-item>
          <v-list-item @click="option = 'content'">
            <v-list-item-title>내용</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
      <v-text-field
        v-model="keyword"
        class="mx-2"
        hide-details
        elevation="0"
        outlined
        dense
        prepend-inner-icon='mdi-magnify'
      ></v-text-field>
      <v-btn 
        outlined
        @click="searchBoard"
      >
        검색
      </v-btn>
    </v-toolbar>
    <v-card-text>
      <v-simple-table
        fixed-header
        height="300px"
      >
        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left" style="width: 20%">
                제목
              </th>
              <th class="text-left" style="width: 60%">
                내용
              </th>
              <th class="text-right" style="width: 20%"></th>
            </tr>
          </thead>
          <tbody>
            <tr
              v-for="item in board"
              :key="item.title"
            >
              <td>{{ item.title }}</td>
              <td>{{ item.content }}</td>
              <td>
                <v-btn 
                  icon
                  @click="moveUpdateBoardPage(item.id)"
                >
                  <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn 
                  icon
                  @click="deleteBoard(item.id)"
                >
                  <v-icon>mdi-delete</v-icon>
                </v-btn>
              </td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>
    </v-card-text>
  </v-card>
</template>

<script>
import http from "@/utils/http-common";

  export default {
    name: 'Board',

    data() {
      return {
        board: [],
        select: {
          text: '전체',
          value: 'all'
        },
        keyword: '',
        option: 'all',
        optionToLabel: {
          all: '전체',
          title: '제목',
          content: '내용',
        },
      }
    },
    created() {
      this.getBoardAll();
    },
    methods: {
      getBoardAll() {
        http.get("board").then((res) => {
          this.board = res.data;
        });
      },
      movePostBoardPage() {
        this.$router.push({ name:'BoardPost' });
      },
      moveUpdateBoardPage(id) {
        this.$router.push({ name:'BoardUpdate', params: { id } });
      },
      deleteBoard(id) {
        http.delete("board/" + id)
        .then((res) => {
          if(res.data == 'success') {
            this.getBoardAll();
          }
        });
      },
      searchBoard() {
        http.get(`board/search?option=${this.option}&keyword=${this.keyword}`)
        .then((res) => {
          this.board = res.data;
        });
      }
    }
  }
</script>
