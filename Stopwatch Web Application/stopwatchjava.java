javascript
let startTime = 0;
let updatedTime = 0;
let difference = 0;
let interval = null;
let isRunning = false;
let laps = [];

const display = document.getElementById('display');
const lapContainer = document.getElementById('laps');

function startStopwatch() {
  if (!isRunning) {
    startTime = Date.now() - difference;
    interval = setInterval(updateTime, 1000);
    isRunning = true;
  }
}

function pauseStopwatch() {
  if (isRunning) {
    clearInterval(interval);
    difference = Date.now() - startTime;
    isRunning = false;
  }
}

function resetStopwatch() {
  clearInterval(interval);
  display.textContent = '00:00:00';
  isRunning = false;
  difference = 0;
  laps = [];
  lapContainer.innerHTML = '';
}

function updateTime() {
  updatedTime = Date.now() - startTime;
  const seconds = Math.floor((updatedTime / 1000) % 60);
  const minutes = Math.floor((updatedTime / (1000 * 60)) % 60);
  const hours = Math.floor((updatedTime / (1000 * 60 * 60)) % 24);

  display.textContent =
    (hours < 10 ? '0' + hours : hours) + ':' +
    (minutes < 10 ? '0' + minutes : minutes) + ':' +
    (seconds < 10 ? '0' + seconds : seconds);
}

function recordLap() {
  if (isRunning) {
    const lapTime = display.textContent;
    laps.push(lapTime);
    const lapItem = document.createElement('li');
    lapItem.textContent = `Lap ${laps.length}: ${lapTime}`;
    lapContainer.appendChild(lapItem);
  }
}

document.getElementById('start').addEventListener('click', startStopwatch);
document.getElementById('pause').addEventListener('click', pauseStopwatch);
document.getElementById('reset').addEventListener('click', resetStopwatch);
document.getElementById('lap').addEventListener('click', recordLap);


