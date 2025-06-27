// const BROKER_URL = 'http://localhost:8080/chat'; // SockJS 엔드포인트 URL

// const headers = {
//   Authorization:
//     'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNzUwOTM1NzMzLCJpYXQiOjE3NTA5MzIxMzN9.5w04E4tIl4HlkKzimygdogA3eMjBiCou-HhIEpkuqgs',
// };

// // Stomp.Client 인스턴스 생성
// const stompClient = new Stomp.Client({
//   // SockJS를 사용하여 웹소켓 연결 팩토리 설정
//   webSocketFactory: () => new SockJS(BROKER_URL),

//   // 연결 시 보낼 헤더 설정
//   connectHeaders: headers,

//   // 연결 성공 시 콜백
//   onConnect: (frame) => {
//     console.log('STOMP Connected:', frame);
//     // 여기에 연결 성공 후 수행할 로직 (예: 구독)을 추가합니다.
//     // stompClient.subscribe('/topic/public', (message) => {
//     //   console.log('Received message:', message.body);
//     // });
//   },

//   // 에러 발생 시 콜백
//   onStompError: (frame) => {
//     console.error('STOMP Error:', frame.headers['message']);
//     console.error('Details:', frame.body);
//   },

//   // 디버그 메시지 활성화 (개발 시 유용)
//   debug: (str) => {
//     console.log('STOMP Debug:', str);
//   },

//   // 재연결 지연 시간 (밀리초)
//   reconnectDelay: 5000,
// });

// // 클라이언트 활성화 (연결 시작)
// stompClient.activate();

import { Client } from 'https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/esm/index.min.js';

// test.js의 내용을 여기에 직접 붙여넣거나,
// test.js를 module로 만들고 import 합니다.
// 예: import { connectStomp } from './test.js'; connectStomp();

// test.js의 내용이 여기에 직접 들어간다고 가정합니다.
const BROKER_URL = 'http://localhost:8080/chat';

const headers = {
  Authorization:
    'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNzUwOTM1NzMzLCJpYXQiOjE3NTA5MzIxMzN9.5w04E4tIl4HlkKzimygdogA3eMjBiCou-HhIEpkuqgs',
};

console.log('dasdasdasdas');

// Stomp.Client 대신 임포트한 Client 사용
const stompClient = new Client({
  // <-- 여기에 변경
  webSocketFactory: () => new SockJS(BROKER_URL),
  connectHeaders: headers,
  onConnect: (frame) => {
    console.log('STOMP Connected:', frame);
  },
  onStompError: (frame) => {
    console.error('STOMP Error:', frame.headers['message']);
    console.error('Details:', frame.body);
  },
  debug: (str) => {
    console.log('STOMP Debug:', str);
  },
  reconnectDelay: 5000,
});

stompClient.activate();
