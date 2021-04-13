/**
 * 
 */


var addTask = document.querySelector('.addTask');
var addDrop = document.querySelector('.addDrop');
var closeDrop = document.querySelector('.closeDrop');

var submitTaskBtn = document.querySelector('.submitTaskBtn');

addTask.addEventListener('click', dropAddTask);
closeDrop.addEventListener('click', closeAddTask);

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

var stopDrop = submitTaskBtn.offsetTop + submitTaskBtn.offsetHeight;
async function dropAddTask(){
  var countDrop = 0;
  while(countDrop <= stopDrop){
    await sleep(20);
    countDrop = countDrop + 20;
    addDrop.style.height = countDrop + "px";
  }
  console.log("submit pos: " + submitTaskBtn.offsetHeight);
  // addDrop.style.height = submitTaskBtn.offsetTop + submitTaskBtn.offsetHeight + "px";
}

async function closeAddTask(){
  console.log("test close " );

  var countDrop = submitTaskBtn.offsetTop + submitTaskBtn.offsetHeight;
  while(countDrop >= 0){
    await sleep(20);
    countDrop = countDrop - 20;
    addDrop.style.height = countDrop + "px";
  }
  addDrop.style.height = 0;
}