package com.pocketgator.game.WreckThatShip.entity;



import java.util.ArrayList;
import java.util.List;

import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.triangulation.EarClippingTriangulator;
import org.anddev.andengine.extension.physics.box2d.util.triangulation.ITriangulationAlgoritm;
import org.anddev.andengine.util.constants.Constants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * Will create the bodies for the ships
 * @author Frank
 *
 */
public class PhysicsBodyFactory {

	
	private final static FixtureDef mFixtureDef = PhysicsFactory.createFixtureDef(10f, 0.5f, 0.5f);
	Body rectangle;

	FixtureDef fd;
	public Body[] castleRectangles; 
	public float leftMostSector;
	public float leftSector;
	public float centerSector;
	public float rightSector;
	public float rightMostSector;
	private Body boxBody;
	
	public PhysicsBodyFactory(){
	}
	/**
	 * Creates a physics body for a GreekShip {@link GrekShip}
	 * @param ship
	 * @param mPhysicsWorld
	 * @return
	 */
	public static Body createGreekShipBody(Ship ship, PhysicsWorld mPhysicsWorld){
		final List<Vector2> rectangle_vertices = new ArrayList<Vector2>(); // Add some vertices here!
    	rectangle_vertices.add(new Vector2(-1.296158f, -0.425997f));
    	rectangle_vertices.add(new Vector2(1.351294f, -0.425997f));
    	rectangle_vertices.add(new Vector2(1.351294f, 0.150319f));
    	rectangle_vertices.add( new Vector2(-1.296158f, 0.150319f));
    	rectangle_vertices.add( new Vector2(-0.773872f, -0.101820f));
    	rectangle_vertices.add( new Vector2(0.684928f, -0.101820f));
    	rectangle_vertices.add(new Vector2(0.684928f, 0.546536f));
    	rectangle_vertices.add( new Vector2(-0.773872f, 0.546536f));
    	final ITriangulationAlgoritm triangulationAlgoritm = new EarClippingTriangulator();
    	final List<Vector2> triangles = triangulationAlgoritm.computeTriangles(rectangle_vertices);
    	mFixtureDef.restitution = 0.01f;                       
    	final Body shipBody = PhysicsFactory.createTrianglulatedBody(mPhysicsWorld, ship, triangles, BodyType.DynamicBody, mFixtureDef,true);
    	shipBody.setLinearDamping(0.01f);
    	return shipBody;
	}
	
	public void createCastle(PhysicsWorld pPhysicsWorld){
		FixtureDef tempFixture = PhysicsFactory.createFixtureDef(0.018f, 0.5f, 0.5f);
		BodyDef bodydef = new BodyDef();
		castleRectangles = new Body[13];
	
		for(int i=1; i < 14; i++){
			PolygonShape boxPoly = new PolygonShape();
			switch(i){
			case 1:
				bodydef.position.set(new Vector2(14.568074f, 25 - 6.405854f));
				castleRectangles[0] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices1 = new Vector2[4];
				vertices1[0] = new Vector2(0.956243f, 0.239061f);
				vertices1[1] = new Vector2(-0.956243f, 0.239061f);
				vertices1[2] = new Vector2(-0.956243f, -0.239061f);
				vertices1[3] = new Vector2(0.956243f, -0.239061f);
				boxPoly.set(vertices1);
				tempFixture.shape = boxPoly;
				castleRectangles[0].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 2:
				bodydef.position.set(new Vector2(15.641408f, 25 - 4.659247f));
				castleRectangles[1] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices2 = new Vector2[4];
				vertices2[0] = new Vector2(0.273212f, 1.078213f);
				vertices2[1] = new Vector2(-0.273212f, 1.078213f);
				vertices2[2] = new Vector2(-0.273212f, -1.078213f);
				vertices2[3] = new Vector2(0.273212f, -1.078213f);
				boxPoly.set(vertices2);
				tempFixture.shape = boxPoly;
				castleRectangles[1].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 3:
				bodydef.position.set(new Vector2(15.265741f, 25 - 5.961884f));
				castleRectangles[2] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices3 = new Vector2[4];
				vertices3[0] = new Vector2(-0.170758f, -0.204909f);
				vertices3[1] = new Vector2(0.170758f, -0.204909f);
				vertices3[2] = new Vector2(0.170758f, 0.204909f);
				vertices3[3] = new Vector2(-0.170758f, 0.204909f);
				boxPoly.set(vertices3);
				tempFixture.shape = boxPoly;
				castleRectangles[2].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 4:
				bodydef.position.set(new Vector2(13.197133f, 25 - 6.425370f));
				castleRectangles[3] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices4 = new Vector2[4];
				vertices4[0] = new Vector2(-0.336637f, -0.209788f);
				vertices4[1] = new Vector2(0.336637f, -0.209788f);
				vertices4[2] = new Vector2(0.336637f, 0.209788f);
				vertices4[3] = new Vector2(-0.336637f, 0.209788f);
				boxPoly.set(vertices4);
				tempFixture.shape = boxPoly;
				castleRectangles[3].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 5:
				bodydef.position.set(new Vector2(12.977588f, 25 - 5.971642f));
				castleRectangles[4] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices5 = new Vector2[4];
				vertices5[0] = new Vector2(-0.029273f, -0.214667f);
				vertices5[1] = new Vector2(0.029273f, -0.214667f);
				vertices5[2] = new Vector2(0.029273f, 0.214667f);
				vertices5[3] = new Vector2(-0.029273f, 0.214667f);
				boxPoly.set(vertices5);
				tempFixture.shape = boxPoly;
				castleRectangles[4].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 6:
				bodydef.position.set(new Vector2(11.923769f, 25 - 5.669157f));
				castleRectangles[5] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices6 = new Vector2[4];
				vertices6[0] = new Vector2(-0.975758f, -0.078061f);
				vertices6[1] = new Vector2(0.975758f, -0.078061f);
				vertices6[2] = new Vector2(0.975758f, 0.078061f);
				vertices6[3] = new Vector2(-0.975758f, 0.078061f);
				boxPoly.set(vertices6);
				tempFixture.shape = boxPoly;
				castleRectangles[5].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 7:
				bodydef.position.set(new Vector2(9.821009f, 25 - 5.664278f));
				castleRectangles[6] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices7 = new Vector2[4];
				vertices7[0] = new Vector2(-1.019668f, -0.063424f);
				vertices7[1] = new Vector2(1.019668f, -0.063424f);
				vertices7[2] = new Vector2(1.019668f, 0.063424f);
				vertices7[3] = new Vector2(-1.019668f, 0.063424f);
				boxPoly.set(vertices7);
				tempFixture.shape = boxPoly;
				castleRectangles[6].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 8:
				bodydef.position.set(new Vector2(8.586675f, 25 - 4.966611f));
				castleRectangles[7] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices8 = new Vector2[4];
				vertices8[0] = new Vector2(-0.273212f, -0.048788f);
				vertices8[1] = new Vector2(0.273212f, -0.048788f);
				vertices8[2] = new Vector2(0.273212f, 0.048788f);
				vertices8[3] = new Vector2(-0.273212f, 0.048788f);
				boxPoly.set(vertices8);
				tempFixture.shape = boxPoly;
				castleRectangles[7].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 9:
				bodydef.position.set(new Vector2(8.869645f, 25 - 5.303247f));
				castleRectangles[8] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices9 = new Vector2[4];
				vertices9[0] = new Vector2(-0.048788f, -0.258576f);
				vertices9[1] = new Vector2(0.048788f, -0.258576f);
				vertices9[2] = new Vector2(0.048788f, 0.258576f);
				vertices9[3] = new Vector2(-0.048788f, 0.258576f);
				boxPoly.set(vertices9);
				tempFixture.shape = boxPoly;
				castleRectangles[8].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 10:
				bodydef.position.set(new Vector2(6.449764f, 25 -4.947095f));
				castleRectangles[9] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices10 = new Vector2[4];
				vertices10[0] = new Vector2(-0.751334f, -0.048788f);
				vertices10[1] = new Vector2(0.751334f, -0.048788f);
				vertices10[2] = new Vector2(0.751334f, 0.048788f);
				vertices10[3] = new Vector2(-0.751334f, 0.048788f);
				boxPoly.set(vertices10);
				tempFixture.shape = boxPoly;
				castleRectangles[9].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 11:
				bodydef.position.set(new Vector2(5.742339f, 25 - 4.264065f));
				castleRectangles[10] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices11 = new Vector2[4];
				vertices11[0] = new Vector2(0.053667f, 0.575698f);
				vertices11[1] = new Vector2(-0.053667f, 0.575698f);
				vertices11[2] = new Vector2(-0.053667f, -0.575698f);
				vertices11[3] = new Vector2(0.053667f, -0.575698f);
				boxPoly.set(vertices11);
				tempFixture.shape = boxPoly;
				castleRectangles[10].createFixture(tempFixture);
				boxPoly.dispose();
				break;
			case 12:
				bodydef.position.set(new Vector2(7.527977f, 25 - 5.371550f));
				castleRectangles[11] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices12 = new Vector2[4];
				vertices12[0] = new Vector2(-0.385425f, -0.356152f);
				vertices12[1] = new Vector2(0.385425f, -0.356152f);
				vertices12[2] = new Vector2(0.385425f, 0.356152f);
				vertices12[3] = new Vector2(-0.385425f, 0.356152f);
				boxPoly.set(vertices12);
				tempFixture.shape = boxPoly;
				castleRectangles[11].createFixture(tempFixture);
				boxPoly.dispose();
				break;		
			
			case 13:
				bodydef.position.set(new Vector2(8.074401f, 25 - 4.922702f));
				castleRectangles[12] = pPhysicsWorld.createBody(bodydef);
				bodydef.type = BodyType.StaticBody;
				Vector2[] vertices13 = new Vector2[4];
				vertices13[0] = new Vector2(0.121970f, 0.053667f);
				vertices13[1] = new Vector2(-0.121970f, 0.053667f);
				vertices13[2] = new Vector2(-0.121970f, -0.053667f);
				vertices13[3] = new Vector2(0.121970f, -0.053667f);
				boxPoly.set(vertices13);
				tempFixture.shape = boxPoly;
				castleRectangles[12].createFixture(tempFixture);
				boxPoly.dispose();
				break;		
			}
		}
	}
	/**
	 * Creates the body for the cliff
	 * @param shape
	 * @param mPhysicsWorld
	 * @return
	 */
	public static Body createCliff(Shape shape, PhysicsWorld mPhysicsWorld){
    	final List <Vector2> cliff_vertices = new ArrayList<Vector2>();
    	cliff_vertices.add(new Vector2(-9.099949f, -1.469748f));
    	cliff_vertices.add(new Vector2(7.758587f, -1.469748f));
    	cliff_vertices.add(new Vector2(7.758587f, 1.460740f));
    	cliff_vertices.add(new Vector2(-9.099949f, 1.460740f));
    	cliff_vertices.add(new Vector2(7.396392f, -1.436821f));
    	cliff_vertices.add(new Vector2(10.293953f, 0.374155f));
    	cliff_vertices.add(new Vector2(7.264685f, 1.230252f));

    	
    	
    	final ITriangulationAlgoritm triangulationAlgoritm = new EarClippingTriangulator();
    	final List<Vector2> triangles = triangulationAlgoritm.computeTriangles(cliff_vertices);
    	return PhysicsFactory.createTrianglulatedBody(mPhysicsWorld, shape, triangles, BodyType.StaticBody, mFixtureDef,false);
    }
	
	public void createWaterLevel(PhysicsWorld pPhysicsWorld){
		FixtureDef fixture = PhysicsFactory.createFixtureDef(1f, 0f, 0.5f);
		BodyDef bodyDef = new BodyDef();
		PolygonShape boxPoly = new PolygonShape();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(new Vector2(35.286298f, 25 - 2.86787528684f));
		boxBody = pPhysicsWorld.createBody(bodyDef);
		Vector2[] vertices = new Vector2[4];
		vertices[0] = new Vector2(-12.000000f, -0.071697f);
		vertices[1] = new Vector2(12.000000f, -0.071697f);
		vertices[2] = new Vector2(12.000000f, 0.071697f);
		vertices[3] = new Vector2(-12.000000f, 0.071697f);
		boxPoly.set(vertices);
		fixture.shape = boxPoly;
		boxBody.createFixture(fixture);
		
		this.centerSector = (bodyDef.position.x) * 32;
		this.leftSector = this.centerSector - 6 * 32;
		this.leftMostSector = this.leftSector - 6  * 32;
		this.rightSector = this.centerSector + 6  * 32;
		this.rightMostSector = this.rightSector + 6  * 32;
		
	}
	public static Body createCircle(Shape pShape, PhysicsWorld mPhysicsWorld){
		FixtureDef fixture = PhysicsFactory.createFixtureDef(1f, 0f, 0f);
		fixture.restitution = 0.2f;
		BodyDef bodydef = new BodyDef();
		
		final float[] sceneCenterCoordinates = pShape.getSceneCenterCoordinates();
		bodydef.position.x = sceneCenterCoordinates[Constants.VERTEX_INDEX_X] / 32;
		bodydef.position.y = sceneCenterCoordinates[Constants.VERTEX_INDEX_Y] / 32;
		bodydef.type = BodyType.DynamicBody;
		CircleShape circle = new CircleShape();
		Body fireBall = mPhysicsWorld.createBody(bodydef);
		circle.setRadius(0.385516f);
		fixture.shape = circle;
		fireBall.createFixture(fixture);
		return fireBall;
	}
	

	
	public void createGround(PhysicsWorld mPhysicsWorld){
		BodyDef bodyDef = new BodyDef();
		FixtureDef fd = new FixtureDef();
		bodyDef.position.set(33.968033f, 25 - 1.849771f);
		

		bodyDef.angle = 0f;
		bodyDef.type = BodyType.StaticBody;
		rectangle = mPhysicsWorld.createBody(bodyDef);
		
		PolygonShape pShape = new PolygonShape() ;
		
		fd.density = 0.015000f;
		fd.friction = 0.300000f;
		fd.restitution = 0.600000f;
		
		Vector2[] rectangle_vertices = new Vector2[4];
		rectangle_vertices[0] = new Vector2(-12.264450f, -0.979230f);
		rectangle_vertices[1] = new Vector2(12.264450f, -0.979230f);
		rectangle_vertices[2] = new Vector2(12.264450f, 0.979230f);
		rectangle_vertices[3] = new Vector2(-12.264450f, 0.979230f);
		pShape.set(rectangle_vertices);
		fd.shape = pShape;
	
		rectangle.createFixture(fd);
		rectangle.getPosition();
	}
	
	
	public Body[] getCastleBodies(){
		return this.castleRectangles;
	}
	
	public Body getBoxBody(){
		return this.boxBody;
	}
}
