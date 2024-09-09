<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>
<hr />
<html>
<head>
    <title>열품타 - 공부 타이머</title>
    <style>
        body {
            font-family: '맑은 고딕', sans-serif;
            background-color: #f5ebe8;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #ff6b6b;
            padding: 20px;
            text-align: center;
            color: white;
        }

        .header h1 {
            margin: 0;
            font-size: 2em;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 20px;
        }

        .timer-section, .subject-section {
            background-color: white;
            border-radius: 10px;
            padding: 30px;
            margin: 10px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            width: 45%;
        }

        .timer-section h2, .subject-section h2 {
            margin-top: 0;
        }

        .timer-display {
            font-size: 5em;
            text-align: center;
            margin: 20px 0;
        }

        .start-button, .stop-button {
            font-size: 1em;
            padding: 15px 30px;
            margin: 5px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
        }

        .start-button {
            background-color: #6bcB77;
            color: white;
        }

        .stop-button {
            background-color: #ff6b6b;
            color: white;
        }

        .subject-form input[type="text"] {
            width: calc(100% - 100px);
            padding: 10px;
            font-size: 1em;
        }

        .subject-form button {
            width: 80px;
            padding: 10px;
            font-size: 1em;
            margin-left: 10px;
            cursor: pointer;
            background-color: #4D96FF;
            color: white;
            border: none;
            border-radius: 5px;
        }

        .subject-list {
            margin-top: 20px;
        }

        .subject-list table {
            width: 100%;
            border-collapse: collapse;
        }

        .subject-list th, .subject-list td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        .subject-list th {
            background-color: #f2f2f2;
        }

        .subject-button {
            padding: 5px 10px;
            font-size: 0.9em;
            cursor: pointer;
            background-color: #6bcB77;
            color: white;
            border: none;
            border-radius: 5px;
        }

    </style>
    <script>
        let timer;
        let totalSeconds = 0;

        function startTimer() {
            const selectedSubject = document.getElementById("selectedSubject").value;
            if (!selectedSubject) {
                alert("과목을 선택해주세요.");
                return;
            }
            document.getElementById("startButton").disabled = true;
            document.getElementById("stopButton").disabled = false;
            timer = setInterval(setTime, 1000);
        }

        function stopTimer() {
            clearInterval(timer);
            document.getElementById("startButton").disabled = false;
            document.getElementById("stopButton").disabled = true;

            // 서버에 공부 종료 요청 보내기
            const logId = document.getElementById("logId").value;
            window.location.href = "/usr/study/end?logId=" + logId;
        }

        function setTime() {
            ++totalSeconds;
            const hours = Math.floor(totalSeconds / 3600);
            const minutes = Math.floor((totalSeconds - (hours * 3600)) / 60);
            const seconds = totalSeconds % 60;

            document.getElementById("timer").innerHTML =
                (hours < 10 ? "0" + hours : hours) + ":" +
                (minutes < 10 ? "0" + minutes : minutes) + ":" +
                (seconds < 10 ? "0" + seconds : seconds);
        }
    </script>
</head>
<body>
    <div class="header">
        <h1>열품타 - 공부 타이머</h1>
    </div>

    <div class="container">
        <!-- 타이머 섹션 -->
        <div class="timer-section">
            <h2>공부 타이머</h2>
            <div class="timer-display" id="timer">00:00:00</div>
            <select id="selectedSubject">
                <option value="">과목 선택</option>
                <c:forEach var="subject" items="${subjects}">
                    <option value="${subject.id}">${subject.subjectName}</option>
                </c:forEach>
            </select>
            <input type="hidden" id="logId" value="${log.id}" />
            <div>
                <button class="start-button" id="startButton" onclick="startTimer()">시작</button>
                <button class="stop-button" id="stopButton" onclick="stopTimer()" disabled>정지</button>
            </div>
        </div>

        <!-- 과목 추가 섹션 -->
        <div class="subject-section">
            <h2>과목 관리</h2>
            <form class="subject-form" action="/usr/study/createSubject" method="post">
                <input type="text" name="subjectName" placeholder="새 과목 이름" required />
                <button type="submit">추가</button>
            </form>

            <div class="subject-list">
                <table>
                    <thead>
                        <tr>
                            <th>과목명</th>
                            <th>공부 시작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="subject" items="${subjects}">
                            <tr>
                                <td>${subject.subjectName}</td>
                                <td>
                                    <button class="subject-button" onclick="startStudy(${subject.id})">공부 시작</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script>
        function startStudy(subjectId) {
            window.location.href = "/usr/study/start?subjectId=" + subjectId;
        }
    </script>
</body>
</html>