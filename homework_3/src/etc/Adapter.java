/*
 * File: Adapter.java
 * Path: ./etc/
 *
 * Date: 2026-01-12
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Adapter template
 */

package etc;

import java.lang.Math;

interface Trigonometry {
    double sin( double gradus );
    double cos( double gradus );
    double tg( double gradus );
}

class RemoteNode {
    public double cos( double radian ) {
        return Math.cos( radian );
    }
}

class ClientNode extends RemoteNode implements Trigonometry {
    private final RemoteNode remoteNode;

    public ClientNode( RemoteNode node ) throws NullPointerException {
        if ( node == null )
            throw new NullPointerException( "class ClientNode: ClientNode(null)" );
        remoteNode = node;
    }

    private double radianToGradus( double radian ) {
        return radian * 180 / Math.PI;
    }

    private double gradusToRadian( double gradus ) {
        return gradus * Math.PI / 180;
    }

    public double sin( double gradus ) {
        double radian = Math.PI/2 - gradusToRadian( gradus );
        return remoteNode.cos( radian );
    }

    public double cos( double gradus ) {
        double radian = gradusToRadian( gradus );
        return remoteNode.cos( radian );
    }

    public double tg( double gradus ) {
        double cosValue = cos( gradus );
        if ( Math.abs( cosValue ) < 0.00001 ) return Double.NaN;
        return sin( gradus ) / cos( gradus );
    }
}

public class Adapter {
    private final static String MODULE_INFO =
            "This module not for production use. Only for studying purpose.";

    public static void main( String[] args ) {
        final RemoteNode remoteNode = new RemoteNode();
        final ClientNode clientNode = new ClientNode( remoteNode );
        System.out.println( "cos(0)=" + remoteNode.cos( 0 ) + "; cos( PI / 4 )=" +
                remoteNode.cos( Math.PI / 4 ) + "; cos( PI / 3 )=" + remoteNode.cos( Math.PI / 3 ) +
                "; cos( PI / 2 )=" + remoteNode.cos( Math.PI / 2 ) );
        System.out.println( "cos(0)=" + clientNode.cos( 0 ) + "; cos(45)=" + clientNode.cos( 45 ) +
                "; cos(60)=" + clientNode.cos( 60 ) + "; cos(90)=" + clientNode.cos( 90 ) );
        System.out.println( "sin(0)=" + clientNode.sin( 0 ) + "; sin(45)=" + clientNode.sin( 45 ) +
                "; sin(60)=" + clientNode.sin( 60 ) + "; sin(90)=" + clientNode.sin( 90 ) );
        System.out.println( "tg(0)=" + clientNode.tg( 0 ) + "; tg(30)=" + clientNode.tg( 30 ) +
                "; tg(45)=" + clientNode.tg( 45 ) + "; tg(60)=" + clientNode.tg( 60 ) );
    }
}
