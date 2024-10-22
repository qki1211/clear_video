package io.quantumknight.video.gui.elements;
/********************************************************************************************
//* Filename: 		ToggleSwitch.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Swing Toggle Switch Element - Dynamic
//* 				
//* 				
//* ******************************************************************************************
//* 				
//* 
//* 				SOFTWARE LICENSE AGREEMENT:
//* 				--------------------------------------------------------------------------
//* 				Licensed under the Apache License, Version 2.0 (the "License");
//* 				you may not use this file except in compliance with the License.
//* 				You may obtain a copy of the License at
//* 
//*    					https://www.apache.org/licenses/LICENSE-2.0
//* 
//* 				Unless required by applicable law or agreed to in writing, software
//* 				distributed under the License is distributed on an "AS IS" BASIS,
//* 				WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//* 				See the License for the specific language governing permissions and
//* 				limitations under the License.
//* 
//* ******************************************************************************************
//* 
//* 				COMMODITY CLASSIFICATION : UNITED STATES DEPARTMENT OF COMMERCE
//* 				--------------------------------------------------------------------------
//* 				THIS ENCRYPTION ITEM PROVIDING AN OPEN CRYPTOGRAPHIC INTERFACE IS AUTHORIZED
//* 				FOR LICENSE EXCEPTION ENC UNDER SECTIONS 740.17 (A) AND (B)(2) OF THE EXPORT
//* 				ADMINISTRATION REGULATIONS (EAR). 
//* 
//* 				UNITED STATES DEPARTMENT OF COMMERCE
//* 				BUREAU OF INDUSTRY AND SECURITY 
//* 				WASHINGTON, D.C. 20230
//* 
//* 				BIS/EA/STC/IT
//* 
/********************************************************************************************/

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import io.quantumknight.video.action.AesEncryptionDisableAction;
import io.quantumknight.video.action.AesEncryptionEnableAction;
import io.quantumknight.video.action.BypassDecryptionDisableAction;
import io.quantumknight.video.action.BypassDecryptionEnableAction;
import io.quantumknight.video.action.EncryptionDisableAction;
import io.quantumknight.video.action.EncryptionEnableAction;
import io.quantumknight.video.action.StartWaveFormAction;
import io.quantumknight.video.action.StopWaveFormAction;
import io.quantumknight.video.constants.ConstantsEncryptionStrength;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsToggleButton;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.screens.HomeScreen;
import io.quantumknight.video.state.VideoDemoSettings;

public class ToggleSwitch extends JPanel {
	
	private static final long serialVersionUID = 1963580478551169116L;

	private boolean activated = false;
    private Color buttonColor  = ConstantsColor.COLOR_WHITE_COLOR;		// WHITE
    private Color switchColor  = ConstantsColor.COLOR_SCREEN_BKG_STD; 	// GREY
    private Color borderColor  = ConstantsColor.COLOR_TEXT_CARD_GREY; 	// LIGHT GREY
    private Color activeSwitch = ConstantsColor.COLOR_LIME_GREEN; 		// GREEN
    
    private BufferedImage puffer;
    
    private int borderRadius = 10;
    private Graphics2D g;
    
    public ToggleSwitch(List<Component> componentsToToggle, ConstantsToggleButton.ToggleButtonType toggleType) {
        super();
        setVisible(true);
        addMouseListener(new MouseAdapter() {
            
        	@Override
            public void mouseReleased(MouseEvent arg0) {
                activated = !activated;
                repaint();
                
                
                // CHANGE SYSTEM STATE
                switch(toggleType) {
                
	                case MAIN: 					// EXECUTE MAIN ENCRYPTION ACTION(S)
	                							toggleMain(componentsToToggle);
	                							if (activated) {
	                								final Object[] input = { ConstantsElements.BUTTON_LABELS[8] }; // Run all actions off EDT
	                								SwingUtilities.invokeLater(new Runnable() {
	                									public void run() {
	                										new EncryptionEnableAction(input);
	                									}
	                								});
	                							}
	                							else {
	                								final Object[] input = { ConstantsElements.BUTTON_LABELS[9] }; // Run all actions off EDT
	                								SwingUtilities.invokeLater(new Runnable() {
	                									public void run() {
	                										new EncryptionDisableAction(input);
	                									}
	                								});
	                							}
	                							break;
	                	
	                case WAVEFORM:				// EXECUTE WAVEFORM CONTROL ACTION(S)
	                							toggleMain(componentsToToggle);
							                	if (activated) {
												
							                		final Object[] input = { ConstantsElements.BUTTON_LABELS[12] }; // Run all actions off EDT
	                								SwingUtilities.invokeLater(new Runnable() {
	                									public void run() {
	                										new StartWaveFormAction(input);
	                									}
	                								});
												}
												else {
													
													final Object[] input = { ConstantsElements.BUTTON_LABELS[13] }; // Run all actions off EDT
	                								SwingUtilities.invokeLater(new Runnable() {
	                									public void run() {
	                										new StopWaveFormAction(input);
	                									}
	                								});
												}
												break;
												
	                case BYPASS:				// EXECUTE DECRYPTION BYPASS ACTION(S)
	                							toggleMain(componentsToToggle);
							                	if (activated) {
													
							                		final Object[] input = { ConstantsElements.BUTTON_LABELS[10] }; // Run all actions off EDT
	                								SwingUtilities.invokeLater(new Runnable() {
	                									public void run() {
	                										new BypassDecryptionEnableAction(input);
	                									}
	                								});
												}
												else {
													
													final Object[] input = { ConstantsElements.BUTTON_LABELS[11] }; // Run all actions off EDT
	                								SwingUtilities.invokeLater(new Runnable() {
	                									public void run() {
	                										new BypassDecryptionDisableAction(input);
	                									}
	                								});
												}
												break;
	                case AES:
							                	// TOGGLE BETWEEN AES and CLEAR
	                							toggleAES(componentsToToggle);
							                	if (activated) {
													
							                		final Object[] input = { ConstantsElements.BUTTON_LABELS[14] }; // Run all actions off EDT
													SwingUtilities.invokeLater(new Runnable() {
														public void run() {
															new AesEncryptionEnableAction(input);
														}
													});
												}
												else {
													
													final Object[] input = { ConstantsElements.BUTTON_LABELS[15] }; // Run all actions off EDT
													SwingUtilities.invokeLater(new Runnable() {
														public void run() {
															new AesEncryptionDisableAction(input);
														}
													});
												}
												break;
                }
            }
        });
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(0, 0, 41, 21);
    }
    
    private void toggleMain(List<Component> componentsToToggle) {
    	
        // UPDATE VISUAL STATE AND CHILD GUI COMPONENTS
        if (componentsToToggle != null) {
        	
        	for (Component c : componentsToToggle) {
            	
        		c.setEnabled(activated);
            	c.setBackground(activated ? ConstantsColor.COLOR_WHITE_COLOR : ConstantsColor.COLOR_LIGHTER_GREY);
            	
            	if (c instanceof JTextField) {
            		JTextField jt = (JTextField)c;
            		jt.setText("");
            	}
            	if (c instanceof JSlider) {
            		c.setBackground(activated ? ConstantsColor.COLOR_DARK_GREY : ConstantsColor.COLOR_LIGHTER_GREY);
            	}
            	if (c instanceof ToggleSwitch) {
            		ToggleSwitch ts = (ToggleSwitch)c;
            		ts.setEnabled(activated);
            		ts.setActivated(false); // toggling main encryption automatically disables waveform
            	}
            	else if (c instanceof JButton) {
            		JButton jb = (JButton)c;
            		jb.setForeground(activated ? ConstantsColor.COLOR_TEXT_BLACK : ConstantsColor.COLOR_ENGLISH_GREY);
            	}
            }
        	
        	updateStrengthLabel(ConstantsToggleButton.ToggleButtonType.MAIN);
        }
    }
    
    private void toggleAES(List<Component> componentsToToggle) {
    	
    	
        // UPDATE VISUAL STATE AND CHILD GUI COMPONENTS
        if (componentsToToggle != null) {
        	
        	for (Component c : componentsToToggle) {
            	
        		c.setEnabled(!activated);
            	c.setBackground(!activated ? ConstantsColor.COLOR_WHITE_COLOR : ConstantsColor.COLOR_LIGHTER_GREY);
            	
            	if (c instanceof JTextField) {
            		JTextField jt = (JTextField)c;
            		jt.setText("");
            	}
            	if (c instanceof JSlider) {
            		c.setBackground(!activated ? ConstantsColor.COLOR_DARK_GREY : ConstantsColor.COLOR_LIGHTER_GREY);
            	}
//            	if (c instanceof ToggleSwitch) {
//            		ToggleSwitch ts = (ToggleSwitch)c;
//            		ts.setEnabled(activated);
//            		ts.setActivated(false); // toggling main encryption automatically disables waveform
//            	}
            	else if (c instanceof JButton) {
            		JButton jb = (JButton)c;
            		jb.setForeground(!activated ? ConstantsColor.COLOR_TEXT_BLACK : ConstantsColor.COLOR_ENGLISH_GREY);
            	}
            }
        }
        updateStrengthLabel(ConstantsToggleButton.ToggleButtonType.AES);
    }
    
    private void updateStrengthLabel(ConstantsToggleButton.ToggleButtonType toggleType) {
    	
    	// GET SLIDER VALUE
        HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);  // SCREEN REFERENCE FOR UPDATING TIMERS
        JSlider slider = (JSlider)screen.getField(ConstantsElements.SCREEN_FIELDS[12]);
    	int value = slider.getValue();
    	
    	// UPDATE SINGLETON
    	VideoDemoSettings xv = VideoDemoSettings.getInstance();
    	xv.setModulatorSetting(value);
    	xv.setEncryptionStrength(ConstantsEncryptionStrength.getStrength(value - 1));
    	
    	// UPDATE PRIMARY STRENGTH SETTING VISUAL
		JLabel strengthLabel = (JLabel)screen.getField(ConstantsElements.LABELS[4]);
		
		int strength = xv.getEncryptionStrength();
		
		if (toggleType == ConstantsToggleButton.ToggleButtonType.AES) {
			
		}
		if (activated) {
			
			switch (toggleType) {
			
				case AES: 	strength = 256;
							break;
							
				case MAIN: 	strength = xv.getEncryptionStrength();
							break;
							
				default: 	strength = xv.getEncryptionStrength();
							break;
			}
		}
		
		strengthLabel.setText("Strength: " + strength);
		strengthLabel.repaint();
    }
    
    
    @Override
    public void paint(Graphics gr) {
        if(g == null || puffer.getWidth() != getWidth() || puffer.getHeight() != getHeight()) {
            puffer = (BufferedImage) createImage(getWidth(), getHeight());
            g = (Graphics2D)puffer.getGraphics();
            RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHints(rh);
        }
        g.setColor(activated?activeSwitch:switchColor);
        g.fillRoundRect(0, 0, this.getWidth()-1,getHeight()-1, 5, borderRadius);
        g.setColor(borderColor);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 5, borderRadius);
        g.setColor(buttonColor);
        if(activated) {
            g.fillRoundRect(getWidth()/2, 1,  (getWidth()-1)/2 -2, (getHeight()-1) - 2,  borderRadius, borderRadius);
            g.setColor(borderColor);
            g.drawRoundRect((getWidth()-1)/2, 0, (getWidth()-1)/2, (getHeight()-1), borderRadius, borderRadius);
        }
        else {
            g.fillRoundRect(1, 1, (getWidth()-1)/2 -2, (getHeight()-1) - 2,  borderRadius, borderRadius);
            g.setColor(borderColor);
            g.drawRoundRect(0, 0, (getWidth()-1)/2, (getHeight()-1), borderRadius, borderRadius);
        }

        gr.drawImage(puffer, 0, 0, null);
    }
    
    public boolean isActivated() {
        return activated;
    }
    
    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    public Color getSwitchColor() {
        return switchColor;
    }
    
    /**
     * Unactivated Background Color of switch
     */
    public void setSwitchColor(Color switchColor) {
        this.switchColor = switchColor;
    }
    public Color getButtonColor() {
        return buttonColor;
    }
    /**
     * Switch-Button color
     */
    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }
    public Color getBorderColor() {
        return borderColor;
    }
    /**
     * Border-color of whole switch and switch-button
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }
    public Color getActiveSwitch() {
        return activeSwitch;
    }
    public void setActiveSwitch(Color activeSwitch) {
        this.activeSwitch = activeSwitch;
    }
    /**
     * @return the borderRadius
     */
    public int getBorderRadius() {
        return borderRadius;
    }
    /**
     * @param borderRadius the borderRadius to set
     */
    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
}
