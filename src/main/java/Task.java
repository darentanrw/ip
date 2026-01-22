public class Task {
  private String taskName;
  private boolean isDone;

  public Task(String taskName) {
      this.taskName = taskName;
      this.isDone = false;
  }

  public void setStatus(boolean isDone) {
      this.isDone = isDone;
  }

  public String toString() {
      return "[" + (isDone ? "X" : " ") + "] " + taskName;
  }
}