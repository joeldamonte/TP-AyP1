package brains;

import java.util.List;

import edu.unlam.snake.brain.Brain;
import edu.unlam.snake.engine.Direction;
import edu.unlam.snake.engine.Point;

public class SnakeEGLHUCG extends Brain {

	// Pueden agregarse todos los atributos necesarios
	int d;
	int distanciaFruta;
	
	int xFrutaCer;
	int yFrutaCer;
	boolean hayObsDer;
	boolean hayObsIzq;
	boolean hayObsUp;
	boolean hayObsDown;
	boolean xBloq;
	boolean yBloq;
	boolean objCosDer;
	boolean objCosUp;
	boolean objCosDown;
	boolean objCosIzq;
	public SnakeEGLHUCG() {
		super("EGLHUCG");
	}

	/**
	 * Retorna el próximo movimiento que debe hacer la serpiente.
	 * @param head, la posición de la cabeza
	 * @param previous, la dirección en la que venía moviéndose
	 */
	public Direction getDirection(Point head, Direction previous) {
		List<Point> fruits = info.getFruits();
		List<Point> snake = info.getSnake();
		List<List<Point>> enemies = info.getEnemies();
		List<Point> obstacles = info.getObstacles();
		
		// completar con la lógica necesaria para mover la serpiente,
		// intentando comer la mayor cantidad de frutas y sobrevivir
		// el mayor tiempo posible.
		d = 100;
		distanciaFruta = 0;
		
		xFrutaCer = 0;
		yFrutaCer = 0;
		//System.out.println(previous);
		hayObsDer = false;
		hayObsIzq = false;
		hayObsUp = false;
		hayObsDown = false;
		yBloq = false;
		xBloq = false;
	
		fruits.forEach( f ->{
			distanciaFruta = Math.abs(f.getX() - head.getX()) + Math.abs(f.getY() - head.getY());		
			if(distanciaFruta < d){
				xFrutaCer = f.getX();
				yFrutaCer = f.getY();
				d = distanciaFruta;
			}
		});
		
		
		
		
		if(xFrutaCer < head.getX() && previous != Direction.RIGHT){
			for(int i = 0; i < obstacles.size(); i++){
				if(obstacles.get(i).getX() == head.getX() - 1 && obstacles.get(i).getY() == head.getY()){
					hayObsIzq = true;
					xBloq = true;
				}
			}
			for(int i = 0;i < snake.size(); i ++){
				if(snake.get(i).getX() == head.getX() - 1 && snake.get(i).getY() == head.getY()){
					hayObsIzq = true;
					xBloq = true;
				}
			}
			enemies.forEach( enem ->{
				enem.forEach( pos ->{
					if((pos.getX() == head.getX() - 1 && pos.getY() == head.getY())
							|| (pos.getX() == head.getX() - 2 && pos.getY() == head.getY())){
						hayObsIzq = true;
						xBloq = true;
					}
				});
			});
			if(!hayObsIzq){
				return Direction.LEFT;					
			}
		}else if(xFrutaCer > head.getX() && previous != Direction.LEFT){
			for(int i = 0; i < obstacles.size(); i++){
				if(obstacles.get(i).getX() == head.getX() + 1 && obstacles.get(i).getY() == head.getY()){
					hayObsDer = true;
					xBloq = true;
				}
			}
			for(int i = 0;i < snake.size(); i ++){
				if(snake.get(i).getX() == head.getX() + 1 && snake.get(i).getY() == head.getY()){
					hayObsDer = true;
					xBloq = true;
				}
			}
			enemies.forEach( enem ->{
				enem.forEach( pos ->{
					if((pos.getX() == head.getX() + 1 && pos.getY() == head.getY())
							|| (pos.getX() == head.getX() + 2 && pos.getY() == head.getY())){
						hayObsDer = true;
						xBloq = true;
					}
				});
			});
			if(!hayObsDer){
				return Direction.RIGHT;
			}
		}
		if(yFrutaCer < head.getY() && previous != Direction.UP){
			for(int i = 0; i < obstacles.size(); i++){
				if(obstacles.get(i).getX() == head.getX() && obstacles.get(i).getY() == head.getY() - 1){
					hayObsDown = true;
					yBloq = true;
				}
			}
			for(int i = 0;i < snake.size(); i ++){
				if(snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() - 1){
					hayObsDown = true;
					yBloq = true;
				}
			}
			enemies.forEach( enem ->{
				enem.forEach( pos ->{
					if((pos.getX() == head.getX() && pos.getY() == head.getY() - 1)
							|| (pos.getX() == head.getX() && pos.getY() == head.getY() - 2)){
						hayObsDown = true;
						yBloq = true;
					}
				});
			});
			if(!hayObsDown){
				return Direction.DOWN;
			}
		}else if(yFrutaCer > head.getY() && previous != Direction.DOWN){
			for(int i = 0; i < obstacles.size(); i++){
				if(obstacles.get(i).getX() == head.getX() && obstacles.get(i).getY() == head.getY() + 1){
					hayObsUp = true;
					yBloq = true;
				}
			}
			for(int i = 0;i < snake.size(); i ++){
				if(snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() + 1){
					hayObsUp = true;
					yBloq = true;
				}
			}
			enemies.forEach( enem ->{
				enem.forEach( pos ->{
					if((pos.getX() == head.getX() && pos.getY() == head.getY() + 1)
							|| (pos.getX() == head.getX() && pos.getY() == head.getY() + 2)){
						hayObsUp = true;
						yBloq = true;
					}
				});
			});
			if(!hayObsUp){
				return Direction.UP;
			}
		}
		
		objCosDer = false;
		objCosUp = false;
		objCosIzq = false;
		objCosDown = false;
		for(int i = 0; i < obstacles.size(); i++){
			if(obstacles.get(i).getX() == head.getX() + 1 && obstacles.get(i).getY() == head.getY()){
				objCosDer = true;
			}
			if(obstacles.get(i).getX() == head.getX() && obstacles.get(i).getY() == head.getY() + 1){
				objCosUp = true;
			}
			if(obstacles.get(i).getX() == head.getX() - 1 && obstacles.get(i).getY() == head.getY()){
				objCosIzq = true;
			}
			if(obstacles.get(i).getX() == head.getX() && obstacles.get(i).getY() == head.getY() - 1){
				objCosDown = true;
			}
		}
		
		for(int i = 0;i < snake.size(); i ++){
			if((snake.get(i).getX() == head.getX() + 1 && snake.get(i).getY() == head.getY())
					||(snake.get(i).getX() == head.getX() + 2 && snake.get(i).getY() == head.getY())){
				objCosDer = true;
			}
			if((snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() + 1)
					||(snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() + 2)){
				objCosUp = true;
			}
			if((snake.get(i).getX() == head.getX() - 1 && snake.get(i).getY() == head.getY())
					||(snake.get(i).getX() == head.getX() - 2 && snake.get(i).getY() == head.getY())){
				objCosIzq = true;
			}
			if((snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() - 1)
					||(snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() - 2)){
				objCosDown = true;
			}
		}
		
		enemies.forEach( enem ->{
			enem.forEach( pos ->{
				if(pos.getX() == head.getX() + 1 && pos.getY() == head.getY()){
					objCosDer = true;
				}	
				if(pos.getX() == head.getX() && pos.getY() == head.getY() + 1){
					objCosUp = true;
				}
				if(pos.getX() == head.getX() - 1 && pos.getY() == head.getY()){
					objCosIzq = true;
				}
				if(pos.getX() == head.getX() && pos.getY() == head.getY() - 1){
					objCosDown = true;
				}
			});
		});

			if(!objCosIzq){
				return Direction.LEFT;	
			} else if(!objCosUp){
				return Direction.UP;
			}else if(!objCosDer){
				return Direction.RIGHT;
			}else {
				return Direction.DOWN;
			}			




			//return previous.turnRight();
		
		
		
	}
}
