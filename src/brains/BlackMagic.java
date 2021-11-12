package brains;

import java.util.List;

import edu.unlam.snake.brain.Brain;
import edu.unlam.snake.engine.Direction;
import edu.unlam.snake.engine.Point;

public class BlackMagic extends Brain {

	// Pueden agregarse todos los atributos necesarios
	int d;
	int distanciaFruta;
	int xFrutaCer;
	int yFrutaCer;
	
	boolean objCosDer;
	boolean objCosUp;
	boolean objCosDown;
	boolean objCosIzq;
	
	Point pUnLado;
	Point pOtroLado;
	Point pUnLado2;
	Point pOtroLado2;
	
	
	public BlackMagic() {
		super("Black Magic");
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

		
		d = Integer.MAX_VALUE;
		distanciaFruta = 0;

		objCosDer = false;
		objCosUp = false;
		objCosIzq = false;
		objCosDown = false;
		
		this.verificarFrutaMasCercana(fruits, head);
		this.verificarPosicionObstaculos(obstacles, head);
		this.verificarPosicionCuerpoSnake(snake, head, obstacles);
		this.verificarPosicionSnakesEnemigas(enemies, head);
		
		if(yFrutaCer > head.getY() && previous != Direction.DOWN && !objCosUp){
			return Direction.UP;
		}	
		if(yFrutaCer < head.getY() && previous != Direction.UP && !objCosDown){
			return Direction.DOWN;	
		}
		if(xFrutaCer < head.getX() && previous != Direction.RIGHT && !objCosIzq){
			return Direction.LEFT;
		}
		if(xFrutaCer > head.getX() && previous != Direction.LEFT && !objCosDer){
			return Direction.RIGHT;
		}
		
		
		if(previous != Direction.UP && !objCosDown ){
			return Direction.DOWN;	
		} else if(previous != Direction.DOWN && !objCosUp){
			return Direction.UP;
		}else if(previous != Direction.LEFT && !objCosDer){
			return Direction.RIGHT;
		}else if(previous != Direction.RIGHT && !objCosIzq){
			return Direction.LEFT;								
		}
	
		return previous.turnRight();
	
	}

	private void verificarFrutaMasCercana(List<Point> fruits, Point head){
		fruits.forEach( f ->{
			distanciaFruta = Math.abs(f.getX() - head.getX()) + Math.abs(f.getY() - head.getY());
			if(distanciaFruta < d){
				xFrutaCer = f.getX();
				yFrutaCer = f.getY();
				d = distanciaFruta;
			}
		});
	}
	
	private void verificarPosicionObstaculos(List<Point> obstacles, Point head){
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
	}
		
	private void verificarPosicionCuerpoSnake(List<Point> snake, Point head, List<Point> obstacles){
		

		for(int i = 0;i < snake.size(); i ++){	
			if(snake.get(i).getX() == head.getX() - 1 && snake.get(i).getY() == head.getY()){
				objCosIzq = true;
			} else if ((snake.get(i).getX() == head.getX() - 2 && snake.get(i).getY() == head.getY())
					|| (snake.get(i).getX() == head.getX() - 3 && snake.get(i).getY() == head.getY())
					){
				
				pUnLado = new Point(head.getX() - 1,head.getY() + 1);
				pOtroLado = new Point(head.getX() - 1,head.getY() - 1);
				
				if(	(obstacles.contains(pUnLado) || snake.contains(pUnLado)) 
						&& 
					(obstacles.contains(pOtroLado) || snake.contains(pOtroLado))){
					objCosIzq = true;
				}
			} 
			if(snake.get(i).getX() == head.getX() + 1 && snake.get(i).getY() == head.getY()){
				objCosDer = true;
			} else if((snake.get(i).getX() == head.getX() + 2 && snake.get(i).getY() == head.getY())
					|| (snake.get(i).getX() == head.getX() + 3 && snake.get(i).getY() == head.getY())
					){
				
				pUnLado = new Point(head.getX() + 1,head.getY() + 1);
				pOtroLado = new Point(head.getX() + 1,head.getY() - 1);
				if(	(obstacles.contains(pUnLado) || snake.contains(pUnLado)) 
						&& 
					(obstacles.contains(pOtroLado) || snake.contains(pOtroLado))){
					objCosDer = true;
				}
						
			}
			if(snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() - 1){
				objCosDown = true;
			} else if((snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() - 2)
					|| (snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() - 3)
					){
			
				pUnLado = new Point(head.getX() + 1,head.getY() - 1);
				pOtroLado = new Point(head.getX() - 1,head.getY() - 1);
			
				if(	(obstacles.contains(pUnLado) || snake.contains(pUnLado)) 
						&&
					(obstacles.contains(pOtroLado) || snake.contains(pOtroLado))){
					objCosDown = true;
				}
			} 
			if(snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() + 1){
				objCosUp = true;
			} else if((snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() + 2)
					|| (snake.get(i).getX() == head.getX() && snake.get(i).getY() == head.getY() + 3)
					){			
				
				pUnLado = new Point(head.getX() + 1,head.getY() + 1);
				pOtroLado = new Point(head.getX() - 1,head.getY() + 1);

				if(	(obstacles.contains(pUnLado) || snake.contains(pUnLado)) 
						&& 
					(obstacles.contains(pOtroLado) || snake.contains(pOtroLado))){
					objCosUp = true;
				}	
			}
		}
	}
	
	private void verificarPosicionSnakesEnemigas(List<List<Point>> enemies, Point head){
		enemies.forEach( enem ->{
			enem.forEach( pos ->{
				if((pos.getX() == head.getX() + 1 && pos.getY() == head.getY())
						|| (pos.getX() == head.getX() + 2 && pos.getY() == head.getY())){
					objCosDer = true;
				}	
				if((pos.getX() == head.getX() && pos.getY() == head.getY() + 1)
						|| (pos.getX() == head.getX() && pos.getY() == head.getY() + 2)){
					objCosUp = true;
				}
				if((pos.getX() == head.getX() - 1 && pos.getY() == head.getY())
						|| (pos.getX() == head.getX()- 2 && pos.getY() == head.getY())){
					objCosIzq = true;
				}
				if((pos.getX() == head.getX() && pos.getY() == head.getY() - 1)
						|| (pos.getX() == head.getX() && pos.getY() == head.getY() - 2)){
					objCosDown = true;
				}
			});
		});
	}
	
}