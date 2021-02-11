package com.thef1xer.gateclient.asm.visitors;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

/*
Posts an SendPacketEvent every time that a Packet is being sent
 */
public class NetHandlerPlayClientVisitor extends ClassVisitor {
    final String SEND_PACKET;
    final String SEND_PACKET_DESCRIPTOR;

    public NetHandlerPlayClientVisitor(ClassVisitor classVisitor, boolean isObfuscated) {
        super(ASM5, classVisitor);
        this.SEND_PACKET = isObfuscated ? "a" : "sendPacket";
        this.SEND_PACKET_DESCRIPTOR = isObfuscated ? "(Lht;)V" : "(Lnet/minecraft/network/Packet;)V";
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals(SEND_PACKET) && desc.equals(SEND_PACKET_DESCRIPTOR)) {
            mv = new SendPacketVisitor(mv);
            System.out.println("Method " + SEND_PACKET + " transformed");
        }
        return mv;
    }

    public static class SendPacketVisitor extends MethodVisitor {

        public SendPacketVisitor(MethodVisitor mv) {
            super(ASM5, mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKESTATIC, "com/thef1xer/gateclient/util/EventFactory", "sendPacket", "(Lnet/minecraft/network/Packet;)Lnet/minecraft/network/Packet;", false);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            Label l0 = new Label();
            mv.visitJumpInsn(IFNONNULL, l0);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(69, l3);
            mv.visitInsn(RETURN);
            mv.visitLabel(l0);
            mv.visitFrame(F_SAME, 0, null, 0, null);
        }
    }
}
