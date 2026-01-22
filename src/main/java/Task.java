public class Task {
  private String taskName;
  private boolean isDone;
  private String type = null;

  public Task(String taskName) {
      this.taskName = taskName;
      this.isDone = false;
      this.type = null;
  }

  public Task(String taskName, String type) {
    this.taskName = taskName;
    this.isDone = false;
    this.type = type;

    System.out.println("\tGot it. I've added this task: ");
}

  public void setStatus(boolean isDone) {
      this.isDone = isDone;
  }

  public String toString() {
    if (type != null) {
      return "[" + type + "] " + "[" + (isDone ? "X" : " ") + "] " + taskName;
    }
      return "[" + (isDone ? "X" : " ") + "] " + taskName;
  }
}