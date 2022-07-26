/*
 * MCreator (https://mcreator.net/)
 * Copyright (C) 2012-2020, Pylo
 * Copyright (C) 2020-2021, Pylo, opensource contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.mcreator.ui.minecraft.potions;

import net.mcreator.element.types.Potion;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.help.IHelpContext;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.minecraft.JEntriesList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JPotionList extends JEntriesList {

	private final List<JPotionListEntry> entryList = new ArrayList<>();

	private final JPanel entries = new JPanel(new GridLayout(0, 1, 5, 5));

	public JPotionList(MCreator mcreator, IHelpContext gui) {
		super(mcreator, new BorderLayout(), gui);
		setOpaque(false);

		JPanel topbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topbar.setBackground((Color) UIManager.get("MCreatorLAF.LIGHT_ACCENT"));

		add.setText(L10N.t("elementgui.potion.add_entry"));
		topbar.add(add);

		add("North", topbar);

		entries.setOpaque(false);

		add.addActionListener(e -> {
			JPotionListEntry entry = new JPotionListEntry(mcreator, gui, entries, entryList);
			registerEntryUI(entry);
		});

		add("Center", PanelUtils.pullElementUp(entries));

		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder((Color) UIManager.get("MCreatorLAF.BRIGHT_COLOR"), 1),
				L10N.t("elementgui.potion.effects"), 0, 0, getFont().deriveFont(12.0f),
				(Color) UIManager.get("MCreatorLAF.BRIGHT_COLOR")));
	}

	@Override public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		add.setEnabled(false);
	}

	public List<Potion.CustomEffectEntry> getEffects() {
		return entryList.stream().map(JPotionListEntry::getEntry).filter(Objects::nonNull).collect(Collectors.toList());
	}

	public void setEffects(List<Potion.CustomEffectEntry> pool) {
		pool.forEach(e -> {
			JPotionListEntry entry = new JPotionListEntry(mcreator, gui, entries, entryList);
			registerEntryUI(entry);
			entry.setEntry(e);
		});
	}
}