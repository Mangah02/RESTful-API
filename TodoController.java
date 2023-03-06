@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    
    @GetMapping("")
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable(value = "id") Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            return ResponseEntity.ok().body(todo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable(value = "id") Long id, @RequestBody Todo todoDetails) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setTitle(todoDetails.getTitle());
            todo.setCompleted(todoDetails.getCompleted());
            return ResponseEntity.ok().body(todoRepository.save(todo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable(value = "id") Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            todoRepository.delete(optionalTodo.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
