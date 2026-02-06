import Foundation

struct GitaData {
    static let chapters: [Chapter] = [
        Chapter(
            id: 1,
            name: "The Conflict of Arjuna",
            sanskritName: "Arjuna Vishada Yoga",
            summary: "Arjuna faces a crisis of conscience on the battlefield of Kurukshetra, paralysed by the prospect of fighting his own kin. This mirrors the dilemmas every leader faces when duty conflicts with personal bonds.",
            verseCount: 47,
            ceoInsight: "Every CEO faces an Arjuna moment — when the right strategic decision conflicts with personal loyalty. Chapter 1 teaches us that acknowledging the conflict is the first step toward resolution.",
            verses: [
                Verse(
                    id: "1.1",
                    chapterId: 1,
                    verseNumber: 1,
                    sanskrit: "धर्मक्षेत्रे कुरुक्षेत्रे समवेता युयुत्सवः ।\nमामकाः पाण्डवाश्चैव किमकुर्वत सञ्जय ॥",
                    transliteration: "dharma-kshetre kuru-kshetre samavetā yuyutsavaḥ\nmāmakāḥ pāṇḍavāś caiva kim akurvata sañjaya",
                    translation: "On the sacred field of Kurukshetra, assembled and eager to fight, what did my sons and the sons of Pandu do, O Sanjaya?",
                    commentary: "The blind king Dhritarashtra asks about the battlefield — a field of dharma (righteousness). His blindness is both physical and moral, representing leaders who refuse to see inconvenient truths.",
                    corporateWisdom: "A boardroom is a dharma-kshetra — a field where right and wrong compete. The CEO who refuses to see the full picture, like Dhritarashtra, makes decisions from blindness. True leadership begins with opening your eyes to all stakeholders.",
                    familyWisdom: "Family conflicts, like corporate ones, begin when we choose not to see. Dhritarashtra's denial about his sons' wrongdoing mirrors how family patriarchs sometimes turn a blind eye to dysfunction.",
                    themes: [.leadership, .ethicsIntegrity]
                ),
                Verse(
                    id: "1.32",
                    chapterId: 1,
                    verseNumber: 32,
                    sanskrit: "किं नो राज्येन गोविन्द किं भोगैर्जीवितेन वा ।",
                    transliteration: "kiṁ no rājyena govinda kiṁ bhogair jīvitena vā",
                    translation: "Of what use is a kingdom, O Krishna, or enjoyment, or even life itself?",
                    commentary: "Arjuna questions the very purpose of power and wealth when those he loves stand on the opposing side. This is the fundamental existential question every successful person must face.",
                    corporateWisdom: "This is the question every CEO must honestly answer: What is the purpose of all this wealth, power, and position if it costs you your relationships and inner peace? Success without significance is hollow.",
                    familyWisdom: "Many executives achieve everything professionally only to realise they have lost connection with their families. Arjuna's lament reminds us to ask: what good is the corner office if the dinner table is empty?",
                    themes: [.familyBalance, .selfMastery]
                ),
                Verse(
                    id: "1.47",
                    chapterId: 1,
                    verseNumber: 47,
                    sanskrit: "एवमुक्त्वार्जुनः सङ्ख्ये रथोपस्थ उपाविशत् ।\nविसृज्य सशरं चापं शोकसंविग्नमानसः ॥",
                    transliteration: "evam uktvārjunaḥ saṅkhye rathopastha upāviśat\nvisṛjya sa-śaraṁ cāpaṁ śoka-saṁvigna-mānasaḥ",
                    translation: "Having spoken thus, Arjuna cast aside his bow and arrows and sat down on his chariot, his mind overwhelmed with grief.",
                    commentary: "Arjuna's collapse is not weakness — it is the necessary breakdown before breakthrough. Only by fully experiencing his doubt can he become ready for Krishna's transformative wisdom.",
                    corporateWisdom: "Burnout and decision paralysis are not signs of failure. They are signals that you need deeper wisdom. The strongest leaders are those who pause, acknowledge their vulnerability, and seek counsel before acting.",
                    familyWisdom: "Sometimes the bravest thing a family leader can do is admit they don't have all the answers. Arjuna's surrender of his weapons is really a surrender of his ego — the prerequisite for growth.",
                    themes: [.stressResilience, .decisionMaking]
                )
            ]
        ),
        Chapter(
            id: 2,
            name: "The Path of Wisdom",
            sanskritName: "Sankhya Yoga",
            summary: "Krishna begins his teaching by distinguishing the eternal soul from the temporary body, and introduces the concept of performing action without attachment to results — the cornerstone of enlightened leadership.",
            verseCount: 72,
            ceoInsight: "Chapter 2 is the CEO's operating manual. It teaches detachment from outcomes while giving your absolute best — the secret to sustainable high performance without burnout.",
            verses: [
                Verse(
                    id: "2.14",
                    chapterId: 2,
                    verseNumber: 14,
                    sanskrit: "मात्रास्पर्शास्तु कौन्तेय शीतोष्णसुखदुःखदाः ।\nआगमापायिनोऽनित्यास्तांस्तितिक्षस्व भारत ॥",
                    transliteration: "mātrā-sparśās tu kaunteya śītoṣṇa-sukha-duḥkha-dāḥ\nāgamāpāyino 'nityās tāṁs titikṣasva bhārata",
                    translation: "The contact of the senses with their objects, O son of Kunti, gives rise to cold and heat, pleasure and pain. They are transient — they come and go. Bear them patiently, O Bharata.",
                    commentary: "Krishna teaches that all sensory experiences — good and bad — are temporary. The wise person endures them without being disturbed, maintaining inner equilibrium.",
                    corporateWisdom: "Market booms and crashes, quarterly wins and losses — they all come and go. The CEO who maintains equanimity through bull and bear markets alike earns the lasting trust of stakeholders.",
                    familyWisdom: "Family life brings seasons of joy and seasons of difficulty. Neither lasts forever. The wisdom to endure tough phases without overreacting, and to enjoy good times without clinging, creates lasting family harmony.",
                    themes: [.stressResilience, .leadership]
                ),
                Verse(
                    id: "2.47",
                    chapterId: 2,
                    verseNumber: 47,
                    sanskrit: "कर्मण्येवाधिकारस्ते मा फलेषु कदाचन ।\nमा कर्मफलहेतुर्भूर्मा ते सङ्गोऽस्त्वकर्मणि ॥",
                    transliteration: "karmaṇy evādhikāras te mā phaleṣu kadācana\nmā karma-phala-hetur bhūr mā te saṅgo 'stv akarmaṇi",
                    translation: "You have the right to perform your duty, but you are not entitled to the fruits of your actions. Never consider yourself the cause of the results, and never be attached to inaction.",
                    commentary: "This is perhaps the most famous verse of the Gita. It does not advocate indifference, but rather total commitment to excellent work while releasing anxiety about outcomes.",
                    corporateWisdom: "This is the ultimate performance philosophy for CEOs: focus relentlessly on the quality of your decisions and execution, not on quarterly stock prices. When you detach from outcomes, paradoxically, better outcomes follow because fear no longer clouds your judgement.",
                    familyWisdom: "Raise your children with love and the best values you can, but release your attachment to who they become. Support your spouse fully without keeping score. The healthiest families are those where giving is unconditional.",
                    themes: [.dutyDharma, .leadership, .selfMastery]
                ),
                Verse(
                    id: "2.48",
                    chapterId: 2,
                    verseNumber: 48,
                    sanskrit: "योगस्थः कुरु कर्माणि सङ्गं त्यक्त्वा धनञ्जय ।\nसिद्ध्यसिद्ध्योः समो भूत्वा समत्वं योग उच्यते ॥",
                    transliteration: "yoga-sthaḥ kuru karmāṇi saṅgaṁ tyaktvā dhanañjaya\nsiddhy-asiddhyoḥ samo bhūtvā samatvaṁ yoga ucyate",
                    translation: "Perform your duties established in yoga, O Arjuna, abandoning attachment, and remaining equal in success and failure. Such equanimity is called yoga.",
                    commentary: "Krishna defines yoga not as physical postures, but as mental equanimity — treating success and failure with the same balanced mind. This is the foundation of effective action.",
                    corporateWisdom: "Equanimity is the CEO's superpower. When a product launch succeeds, don't let hubris set in. When it fails, don't let despair take hold. The leader who remains steady through both inspires confidence across the entire organisation.",
                    familyWisdom: "Bringing the same steady presence home — not riding emotional highs and lows — creates a stable environment where family members feel safe to grow and take risks of their own.",
                    themes: [.selfMastery, .stressResilience]
                ),
                Verse(
                    id: "2.62",
                    chapterId: 2,
                    verseNumber: 62,
                    sanskrit: "ध्यायतो विषयान्पुंसः सङ्गस्तेषूपजायते ।\nसङ्गात्सञ्जायते कामः कामात्क्रोधोऽभिजायते ॥",
                    transliteration: "dhyāyato viṣayān puṁsaḥ saṅgas teṣūpajāyate\nsaṅgāt sañjāyate kāmaḥ kāmāt krodho 'bhijāyate",
                    translation: "Dwelling on sense objects breeds attachment; attachment breeds desire; desire breeds anger.",
                    commentary: "Krishna maps the chain reaction from contemplation to destruction: thinking about objects leads to attachment, then desire, then anger when desire is unfulfilled, then delusion, then ruin.",
                    corporateWisdom: "This is the anatomy of corporate scandal. A CEO fixates on a competitor's success or an unrealistic target. Obsession breeds reckless desire, desire breeds frustration, and frustration breeds the ethical shortcuts that destroy companies.",
                    familyWisdom: "When we constantly compare our family to others — their wealth, their children's achievements, their lifestyle — we breed dissatisfaction that poisons our own home. Contentment is the antidote.",
                    themes: [.ethicsIntegrity, .selfMastery]
                )
            ]
        ),
        Chapter(
            id: 3,
            name: "The Path of Action",
            sanskritName: "Karma Yoga",
            summary: "Krishna teaches that selfless action performed as a duty, without attachment, is superior to renunciation. Work itself becomes a form of worship when done with the right attitude.",
            verseCount: 43,
            ceoInsight: "For the CEO, Chapter 3 resolves the tension between ambition and detachment. You must act — but act as a steward, not an owner. This is the secret of servant leadership.",
            verses: [
                Verse(
                    id: "3.8",
                    chapterId: 3,
                    verseNumber: 8,
                    sanskrit: "नियतं कुरु कर्म त्वं कर्म ज्यायो ह्यकर्मणः ।\nशरीरयात्रापि च ते न प्रसिद्ध्येदकर्मणः ॥",
                    transliteration: "niyataṁ kuru karma tvaṁ karma jyāyo hy akarmaṇaḥ\nśarīra-yātrāpi ca te na prasiddhyed akarmaṇaḥ",
                    translation: "Perform your prescribed duty, for action is better than inaction. Even the maintenance of your body would be impossible through inaction.",
                    commentary: "Krishna firmly tells Arjuna that withdrawal is not an option. Renouncing the world is not the answer — transforming your relationship with action is.",
                    corporateWisdom: "A CEO cannot lead from the sidelines. Analysis paralysis and endless committee meetings are forms of inaction disguised as caution. The Gita demands engaged leadership — make the call, take responsibility, and move forward.",
                    familyWisdom: "Being present for your family is an action, not a passive state. Showing up to the school play, having dinner together, listening actively — these are prescribed duties that no amount of financial provision can replace.",
                    themes: [.dutyDharma, .leadership, .decisionMaking]
                ),
                Verse(
                    id: "3.21",
                    chapterId: 3,
                    verseNumber: 21,
                    sanskrit: "यद्यदाचरति श्रेष्ठस्तत्तदेवेतरो जनः ।\nस यत्प्रमाणं कुरुते लोकस्तदनुवर्तते ॥",
                    transliteration: "yad yad ācarati śreṣṭhas tat tad evetaro janaḥ\nsa yat pramāṇaṁ kurute lokas tad anuvartate",
                    translation: "Whatever a great person does, common people follow. Whatever standards they set, the world pursues.",
                    commentary: "Leaders set the tone not through policy memos but through personal example. Krishna reminds us that people watch what leaders do, not what they say.",
                    corporateWisdom: "Culture flows from the top. If the CEO cuts corners, the organisation will too. If the CEO demonstrates integrity even when it costs revenue, a culture of trust takes root. Your behaviour is your most powerful corporate policy.",
                    familyWisdom: "Children don't listen to lectures — they mirror behaviour. If you show kindness to your spouse, manage anger with grace, and treat everyone with respect, your children absorb these values naturally.",
                    themes: [.leadership, .ethicsIntegrity, .teamBuilding]
                ),
                Verse(
                    id: "3.35",
                    chapterId: 3,
                    verseNumber: 35,
                    sanskrit: "श्रेयान्स्वधर्मो विगुणः परधर्मात्स्वनुष्ठितात् ।\nस्वधर्मे निधनं श्रेयः परधर्मो भयावहः ॥",
                    transliteration: "śreyān sva-dharmo viguṇaḥ para-dharmāt sv-anuṣṭhitāt\nsva-dharme nidhanaṁ śreyaḥ para-dharmo bhayāvahaḥ",
                    translation: "It is far better to perform one's own duty imperfectly than to perform another's duty perfectly. Destruction in one's own duty is better than engagement in another's duty, for to follow another's path is dangerous.",
                    commentary: "Krishna teaches that authenticity trumps imitation. Each person has a unique role to play, and attempting to be someone else leads to inner conflict and failure.",
                    corporateWisdom: "Don't try to be Steve Jobs or Elon Musk. Find your own leadership style, rooted in your authentic strengths. The CEO who copies another's playbook creates a fragile, inauthentic culture. Authenticity in leadership is non-negotiable.",
                    familyWisdom: "Don't try to be the parent your parents were, or the spouse you see in movies. Each family is unique. Play your authentic role — as father, mother, son, daughter — rather than performing someone else's version of it.",
                    themes: [.selfMastery, .dutyDharma, .leadership]
                )
            ]
        ),
        Chapter(
            id: 4,
            name: "The Path of Knowledge",
            sanskritName: "Jnana Karma Sanyasa Yoga",
            summary: "Krishna reveals the ancient lineage of this wisdom and teaches that true knowledge transforms action. The wise person sees inaction in action and action in inaction.",
            verseCount: 42,
            ceoInsight: "Chapter 4 is about knowledge as the ultimate competitive advantage. Not market data or business intelligence, but self-knowledge — understanding your own motivations, biases, and blind spots.",
            verses: [
                Verse(
                    id: "4.7",
                    chapterId: 4,
                    verseNumber: 7,
                    sanskrit: "यदा यदा हि धर्मस्य ग्लानिर्भवति भारत ।\nअभ्युत्थानमधर्मस्य तदात्मानं सृजाम्यहम् ॥",
                    transliteration: "yadā yadā hi dharmasya glānir bhavati bhārata\nabhyutthānam adharmasya tadātmānaṁ sṛjāmy aham",
                    translation: "Whenever there is a decline in righteousness and an increase in unrighteousness, O Arjuna, at that time I manifest myself.",
                    commentary: "Krishna declares that divine intervention occurs precisely when moral order breaks down. This is a promise that the universe self-corrects.",
                    corporateWisdom: "When corporate culture decays — when shortcuts become normal and ethics erode — the organisation needs a transformational leader to restore dharma. That leader could be you. The best turnaround CEOs are those who restore moral order, not just financial order.",
                    familyWisdom: "In every family, there comes a time when old patterns of dysfunction must be broken. Someone must stand up and say 'this stops with me.' That act of moral courage can transform generations.",
                    themes: [.changeManagement, .ethicsIntegrity, .leadership]
                ),
                Verse(
                    id: "4.18",
                    chapterId: 4,
                    verseNumber: 18,
                    sanskrit: "कर्मण्यकर्म यः पश्येदकर्मणि च कर्म यः ।\nस बुद्धिमान्मनुष्येषु स युक्तः कृत्स्नकर्मकृत् ॥",
                    transliteration: "karmaṇy akarma yaḥ paśyed akarmaṇi ca karma yaḥ\nsa buddhimān manuṣyeṣu sa yuktaḥ kṛtsna-karma-kṛt",
                    translation: "One who sees inaction in action and action in inaction is wise among people and is accomplished in all actions.",
                    commentary: "This profound verse teaches paradoxical wisdom: sometimes the most powerful action looks like stillness, and sometimes frantic activity accomplishes nothing.",
                    corporateWisdom: "The CEO who sits quietly in a meeting, listening deeply, may be doing more than the one who dominates every discussion. Strategic patience — knowing when NOT to act — is often the highest form of leadership.",
                    familyWisdom: "Sometimes the most powerful thing a parent can do is be present without fixing. Sitting with a child in their pain, without offering solutions, is action in apparent inaction — and it builds the deepest bonds.",
                    themes: [.strategicThinking, .selfMastery, .decisionMaking]
                ),
                Verse(
                    id: "4.38",
                    chapterId: 4,
                    verseNumber: 38,
                    sanskrit: "न हि ज्ञानेन सदृशं पवित्रमिह विद्यते ।",
                    transliteration: "na hi jñānena sadṛśaṁ pavitram iha vidyate",
                    translation: "In this world, there is nothing as purifying as knowledge.",
                    commentary: "Krishna declares knowledge supreme — not information or data, but deep understanding that transforms the knower.",
                    corporateWisdom: "In the age of data overload, remember: data is not knowledge, and knowledge is not wisdom. The CEO who invests in deep understanding — of self, of customers, of market forces — builds an organisation that survives disruption.",
                    familyWisdom: "Invest in understanding your family members deeply — their fears, their dreams, their love languages. This knowledge, more than any material provision, is the foundation of a thriving family.",
                    themes: [.selfMastery, .strategicThinking]
                )
            ]
        ),
        Chapter(
            id: 5,
            name: "The Path of Renunciation",
            sanskritName: "Karma Sanyasa Yoga",
            summary: "Krishna harmonises the paths of action and renunciation, teaching that true renunciation is not giving up work but giving up attachment to results while continuing to serve.",
            verseCount: 29,
            ceoInsight: "Chapter 5 answers the burning question for executives: Can I pursue ambitious goals and still find inner peace? Yes — by redefining what you renounce. Renounce the ego, not the enterprise.",
            verses: [
                Verse(
                    id: "5.10",
                    chapterId: 5,
                    verseNumber: 10,
                    sanskrit: "ब्रह्मण्याधाय कर्माणि सङ्गं त्यक्त्वा करोति यः ।\nलिप्यते न स पापेन पद्मपत्रमिवाम्भसा ॥",
                    transliteration: "brahmaṇy ādhāya karmāṇi saṅgaṁ tyaktvā karoti yaḥ\nlipyate na sa pāpena padma-patram ivāmbhasā",
                    translation: "One who acts by dedicating all work to the Supreme, without attachment, is untouched by sin, as a lotus leaf is untouched by water.",
                    commentary: "The lotus metaphor is one of the Gita's most beautiful images: the lotus grows in muddy water yet remains pristine. Similarly, one can work in the material world without being corrupted by it.",
                    corporateWisdom: "The lotus CEO operates in the mud of corporate politics, competitive pressure, and financial markets without being stained. How? By anchoring every decision in a higher purpose beyond personal gain. Purpose is the CEO's protective coating.",
                    familyWisdom: "You can be deeply involved in family affairs — finances, disputes, in-law dynamics — without letting them consume your inner peace. Be like the lotus: present in the water, never soaked by it.",
                    themes: [.selfMastery, .ethicsIntegrity, .stressResilience]
                )
            ]
        ),
        Chapter(
            id: 6,
            name: "The Path of Meditation",
            sanskritName: "Dhyana Yoga",
            summary: "Krishna teaches the practice of meditation and self-discipline, describing the characteristics of a person established in yoga — perfectly balanced, self-controlled, and serene.",
            verseCount: 47,
            ceoInsight: "Chapter 6 is the CEO's guide to mental fitness. Just as physical health requires exercise, mental clarity requires disciplined practice. Meditation is not a luxury — it is a leadership necessity.",
            verses: [
                Verse(
                    id: "6.5",
                    chapterId: 6,
                    verseNumber: 5,
                    sanskrit: "उद्धरेदात्मनात्मानं नात्मानमवसादयेत् ।\nआत्मैव ह्यात्मनो बन्धुरात्मैव रिपुरात्मनः ॥",
                    transliteration: "uddhared ātmanātmānaṁ nātmānam avasādayet\nātmaiva hy ātmano bandhur ātmaiva ripur ātmanaḥ",
                    translation: "Elevate yourself by your own mind, and do not degrade yourself. The mind alone is the friend of the self, and the mind alone is the enemy of the self.",
                    commentary: "Krishna teaches radical self-responsibility. No external force lifts you up or brings you down — it is your own mind that is both your greatest ally and your most dangerous adversary.",
                    corporateWisdom: "No executive coach, board advisor, or strategy consultant can save a CEO from their own undisciplined mind. Self-mastery precedes market mastery. The inner game determines the outer game.",
                    familyWisdom: "Don't blame your spouse, children, or parents for your unhappiness. Your mind creates your experience. Master your own thoughts and reactions, and your family life transforms without anyone else needing to change.",
                    themes: [.selfMastery, .stressResilience, .leadership]
                ),
                Verse(
                    id: "6.35",
                    chapterId: 6,
                    verseNumber: 35,
                    sanskrit: "असंशयं महाबाहो मनो दुर्निग्रहं चलम् ।\nअभ्यासेन तु कौन्तेय वैराग्येण च गृह्यते ॥",
                    transliteration: "asaṁśayaṁ mahā-bāho mano durnigrahaṁ calam\nabhyāsena tu kaunteya vairāgyeṇa ca gṛhyate",
                    translation: "Undoubtedly, O mighty-armed one, the mind is restless and very difficult to control. But it can be restrained through practice and detachment, O son of Kunti.",
                    commentary: "Even Krishna acknowledges the difficulty of controlling the mind. But he gives the prescription: abhyasa (consistent practice) and vairagya (dispassion). There are no shortcuts.",
                    corporateWisdom: "Building focus and emotional regulation is like building a company — it requires relentless practice and the willingness to let go of distractions. The most effective CEOs have a daily discipline practice, whether it's meditation, journaling, or contemplation.",
                    familyWisdom: "Patience with family members is a practice, not a personality trait. If your mind races with work stress during family dinner, don't judge yourself — gently bring it back. Over time, presence becomes natural.",
                    themes: [.selfMastery, .stressResilience]
                )
            ]
        ),
        Chapter(
            id: 7,
            name: "Knowledge and Realisation",
            sanskritName: "Jnana Vijnana Yoga",
            summary: "Krishna reveals the nature of the divine and how to perceive the sacred in all of creation. He distinguishes between the lower material nature and the higher spiritual nature.",
            verseCount: 30,
            ceoInsight: "Chapter 7 challenges CEOs to see beyond quarterly numbers. There is a deeper order to the universe, and aligning your organisation with that order creates effortless success.",
            verses: [
                Verse(
                    id: "7.11",
                    chapterId: 7,
                    verseNumber: 11,
                    sanskrit: "बलं बलवतां चाहं कामरागविवर्जितम् ।\nधर्माविरुद्धो भूतेषु कामोऽस्मि भरतर्षभ ॥",
                    transliteration: "balaṁ balavatāṁ cāhaṁ kāma-rāga-vivarjitam\ndharmāviruddho bhūteṣu kāmo 'smi bharatarṣabha",
                    translation: "I am the strength of the strong, devoid of desire and attachment. I am desire itself when it is not contrary to dharma, O chief of the Bharatas.",
                    commentary: "Krishna reveals that divine strength is not about raw power but about power used without selfish attachment. Even desire itself is sacred when aligned with righteousness.",
                    corporateWisdom: "Corporate ambition is not wrong — it is sacred when aligned with dharma. The strength to pursue bold visions becomes divine when freed from ego and personal greed. Build empires that serve, not just extract.",
                    familyWisdom: "The desire to provide for your family, to see your children succeed, to maintain a loving home — these are dharmic desires. Honour them as sacred motivations, not distractions from spiritual life.",
                    themes: [.dutyDharma, .leadership, .ethicsIntegrity]
                )
            ]
        ),
        Chapter(
            id: 9,
            name: "The Royal Knowledge",
            sanskritName: "Raja Vidya Raja Guhya Yoga",
            summary: "Krishna reveals the most sovereign and confidential knowledge — the direct path to the divine through devotion, accessible to all regardless of birth, status, or education.",
            verseCount: 34,
            ceoInsight: "Chapter 9 democratises wisdom. True leadership knowledge is not exclusive to MBAs and pedigrees — it is available to anyone with sincerity. The best insights often come from the most unexpected sources.",
            verses: [
                Verse(
                    id: "9.22",
                    chapterId: 9,
                    verseNumber: 22,
                    sanskrit: "अनन्याश्चिन्तयन्तो मां ये जनाः पर्युपासते ।\nतेषां नित्याभियुक्तानां योगक्षेमं वहाम्यहम् ॥",
                    transliteration: "ananyāś cintayanto māṁ ye janāḥ paryupāsate\nteṣāṁ nityābhiyuktānāṁ yoga-kṣemaṁ vahāmy aham",
                    translation: "To those who worship me with exclusive devotion, always thinking of me, I carry what they lack and preserve what they have.",
                    commentary: "Krishna makes an extraordinary promise: total dedication attracts total support. When you align fully with your purpose, the universe conspires to help you.",
                    corporateWisdom: "When a CEO is fully committed to a righteous mission — not hedging, not half-hearted — resources, talent, and opportunities seem to appear. Total commitment creates its own luck. But the mission must be worthy.",
                    familyWisdom: "When you give yourself fully to your family — truly present, deeply committed — life has a way of providing. The anxiety of 'not enough' dissolves when devotion is complete.",
                    themes: [.leadership, .dutyDharma, .familyBalance]
                )
            ]
        ),
        Chapter(
            id: 11,
            name: "The Universal Form",
            sanskritName: "Vishwarupa Darshana Yoga",
            summary: "Arjuna is granted divine vision to see Krishna's cosmic form — the entire universe contained within one being. This awe-inspiring revelation shows the interconnectedness of all existence.",
            verseCount: 55,
            ceoInsight: "Chapter 11 is about seeing the big picture — really big. The CEO who grasps the interconnectedness of all stakeholders, ecosystems, and generations makes decisions that stand the test of time.",
            verses: [
                Verse(
                    id: "11.33",
                    chapterId: 11,
                    verseNumber: 33,
                    sanskrit: "तस्मात्त्वमुत्तिष्ठ यशो लभस्व\nजित्वा शत्रून् भुङ्क्ष्व राज्यं समृद्धम् ।",
                    transliteration: "tasmāt tvam uttiṣṭha yaśo labhasva\njitvā śatrūn bhuṅkṣva rājyaṁ samṛddham",
                    translation: "Therefore arise and attain glory. Conquer your enemies and enjoy a prosperous kingdom.",
                    commentary: "After revealing the cosmic perspective, Krishna returns to the practical: now that you understand the grand design, get up and play your part in it with full vigour.",
                    corporateWisdom: "Vision without execution is hallucination. After the retreat, after the strategic planning session, after the moment of clarity — arise and execute. The market rewards those who combine cosmic thinking with ground-level action.",
                    familyWisdom: "Understanding your family's place in the larger fabric of life is beautiful. But you still need to get up and do the work — drive carpool, attend the PTA meeting, have the difficult conversation. Spiritual insight must translate to daily action.",
                    themes: [.leadership, .strategicThinking, .dutyDharma]
                )
            ]
        ),
        Chapter(
            id: 12,
            name: "The Path of Devotion",
            sanskritName: "Bhakti Yoga",
            summary: "Krishna describes the qualities of the ideal devotee — compassionate, content, forgiving, self-controlled, and friendly to all. This is also the portrait of the ideal leader.",
            verseCount: 20,
            ceoInsight: "Chapter 12 defines the 'servant leader' millennia before Robert Greenleaf coined the term. The qualities Krishna describes are the same ones that modern leadership research confirms as most effective.",
            verses: [
                Verse(
                    id: "12.13",
                    chapterId: 12,
                    verseNumber: 13,
                    sanskrit: "अद्वेष्टा सर्वभूतानां मैत्रः करुण एव च ।\nनिर्ममो निरहंकारः समदुःखसुखः क्षमी ॥",
                    transliteration: "adveṣṭā sarva-bhūtānāṁ maitraḥ karuṇa eva ca\nnirmamo nirahaṁkāraḥ sama-duḥkha-sukhaḥ kṣamī",
                    translation: "One who is not envious but is a kind friend to all living entities, who does not think of himself as a proprietor, who is free from false ego, equal in both happiness and distress, and always forgiving.",
                    commentary: "Krishna paints the portrait of the evolved person — free from envy, kind, without possessiveness, humble, balanced, and forgiving. This is not weakness; it is the highest form of strength.",
                    corporateWisdom: "The greatest CEOs are not ego-driven empire builders but humble servant leaders. They don't hoard credit, they forgive mistakes that were made in good faith, and they treat the janitor with the same respect as the board chair.",
                    familyWisdom: "Imagine a home where nobody harbours envy, everyone is kind, nobody keeps score, ego is set aside, and forgiveness flows freely. This is the family Krishna envisions — and it starts with one person choosing to embody these qualities.",
                    themes: [.leadership, .teamBuilding, .familyBalance, .conflictResolution]
                )
            ]
        ),
        Chapter(
            id: 14,
            name: "The Three Qualities of Nature",
            sanskritName: "Gunatraya Vibhaga Yoga",
            summary: "Krishna explains the three gunas — sattva (goodness), rajas (passion), and tamas (ignorance) — that govern all of nature and human behaviour. Understanding them is the key to self-awareness.",
            verseCount: 27,
            ceoInsight: "Chapter 14 gives CEOs a powerful framework for understanding organisational culture. Every company operates predominantly in one guna — and the CEO's job is to elevate the culture toward sattva.",
            verses: [
                Verse(
                    id: "14.5",
                    chapterId: 14,
                    verseNumber: 5,
                    sanskrit: "सत्त्वं रजस्तम इति गुणाः प्रकृतिसम्भवाः ।\nनिबध्नन्ति महाबाहो देहे देहिनमव्ययम् ॥",
                    transliteration: "sattvaṁ rajas tama iti guṇāḥ prakṛti-sambhavāḥ\nnibadhnanti mahā-bāho dehe dehinam avyayam",
                    translation: "Material nature consists of three modes — goodness, passion, and ignorance. When the eternal living entity comes in contact with nature, O mighty-armed Arjuna, he becomes conditioned by these modes.",
                    commentary: "The three gunas are the operating system of nature. Sattva brings clarity and peace, rajas brings restless ambition, and tamas brings inertia and confusion. We oscillate between all three.",
                    corporateWisdom: "Diagnose your company culture: Sattvic organisations make thoughtful, ethical decisions. Rajasic ones are hyperactive but burn out. Tamasic ones are stuck in bureaucratic inertia. The CEO's role is to cultivate sattva — clarity, integrity, and purposeful action.",
                    familyWisdom: "Notice your own moods: When you come home sattvic (calm, clear), family interactions flow. When rajasic (stressed, agitated), you create conflict. When tamasic (disengaged, numbed by screens), you're absent even when present. Choose sattva.",
                    themes: [.selfMastery, .teamBuilding, .changeManagement]
                )
            ]
        ),
        Chapter(
            id: 16,
            name: "Divine and Demonic Natures",
            sanskritName: "Daivasura Sampad Vibhaga Yoga",
            summary: "Krishna contrasts 26 divine qualities (fearlessness, truthfulness, compassion) with demonic traits (arrogance, anger, cruelty). This is the Gita's ethical framework for self-assessment.",
            verseCount: 24,
            ceoInsight: "Chapter 16 is the ultimate character audit for leaders. The divine qualities listed here map perfectly to what modern governance frameworks call 'fit and proper' criteria for board directors.",
            verses: [
                Verse(
                    id: "16.1",
                    chapterId: 16,
                    verseNumber: 1,
                    sanskrit: "अभयं सत्त्वसंशुद्धिर्ज्ञानयोगव्यवस्थितिः ।\nदानं दमश्च यज्ञश्च स्वाध्यायस्तप आर्जवम् ॥",
                    transliteration: "abhayaṁ sattva-saṁśuddhir jñāna-yoga-vyavasthitiḥ\ndānaṁ damaś ca yajñaś ca svādhyāyas tapa ārjavam",
                    translation: "Fearlessness, purity of heart, steadfastness in knowledge and yoga, charity, self-control, sacrifice, study of the scriptures, austerity, and honesty.",
                    commentary: "Krishna begins listing the divine qualities, and fearlessness comes first. Not physical bravery, but the moral courage to do what is right regardless of consequences.",
                    corporateWisdom: "This is the CEO's character checklist: fearlessness to make tough calls, purity of intention, commitment to learning, generosity with credit, self-discipline, willingness to sacrifice personal comfort, continuous study, and above all — honesty.",
                    familyWisdom: "The same qualities that make a great leader make a great family member. Fearlessly honest conversations, generosity of spirit, self-control in anger, and the humility to keep learning as a spouse and parent.",
                    themes: [.ethicsIntegrity, .leadership, .selfMastery]
                ),
                Verse(
                    id: "16.21",
                    chapterId: 16,
                    verseNumber: 21,
                    sanskrit: "त्रिविधं नरकस्येदं द्वारं नाशनमात्मनः ।\nकामः क्रोधस्तथा लोभस्तस्मादेतत्त्रयं त्यजेत् ॥",
                    transliteration: "tri-vidhaṁ narakasyedaṁ dvāraṁ nāśanam ātmanaḥ\nkāmaḥ krodhas tathā lobhas tasmād etat trayaṁ tyajet",
                    translation: "There are three gates to self-destruction and hell: lust, anger, and greed. Therefore, one should abandon all three.",
                    commentary: "Krishna identifies the three root causes of downfall with surgical precision: unchecked desire, uncontrolled anger, and insatiable greed. These three destroy individuals and institutions alike.",
                    corporateWisdom: "Every major corporate scandal traces back to one of these three: lust for power, anger at competitors or whistleblowers, or greed for more profit. The board that holds its CEO accountable on these three fronts protects the entire organisation.",
                    familyWisdom: "These same three forces — unchecked desire, explosive anger, and material greed — destroy families just as they destroy companies. Recognising them as enemies is the first step to protecting your home.",
                    themes: [.ethicsIntegrity, .selfMastery, .conflictResolution]
                )
            ]
        ),
        Chapter(
            id: 18,
            name: "The Path of Liberation",
            sanskritName: "Moksha Sanyasa Yoga",
            summary: "The grand finale. Krishna synthesises all his teachings and gives Arjuna the ultimate message: act according to your dharma with full devotion, and leave the rest to the divine.",
            verseCount: 78,
            ceoInsight: "Chapter 18 is the Gita's executive summary. It synthesises everything into one actionable framework: know your duty, do it with excellence, surrender the anxiety about outcomes, and trust the process.",
            verses: [
                Verse(
                    id: "18.46",
                    chapterId: 18,
                    verseNumber: 46,
                    sanskrit: "यतः प्रवृत्तिर्भूतानां येन सर्वमिदं ततम् ।\nस्वकर्मणा तमभ्यर्च्य सिद्धिं विन्दति मानवः ॥",
                    transliteration: "yataḥ pravṛttir bhūtānāṁ yena sarvam idaṁ tatam\nsva-karmaṇā tam abhyarcya siddhiṁ vindati mānavaḥ",
                    translation: "By worshipping through one's own work the One from whom all beings originate and by whom all this is pervaded, a person attains perfection.",
                    commentary: "Work itself becomes worship when performed with the right consciousness. This is Krishna's ultimate message: there is no separation between the sacred and the professional.",
                    corporateWisdom: "Your work IS your spiritual practice. The boardroom is your temple. Every decision made with integrity, every employee treated with dignity, every stakeholder served with care — this is worship through work. The CEO who understands this never burns out, because their work feeds their soul.",
                    familyWisdom: "Cooking dinner for your family, helping with homework, listening to your spouse's day — these mundane acts become sacred when performed with love and full attention. The divine is present in every act of service.",
                    themes: [.dutyDharma, .selfMastery, .leadership]
                ),
                Verse(
                    id: "18.63",
                    chapterId: 18,
                    verseNumber: 63,
                    sanskrit: "इति ते ज्ञानमाख्यातं गुह्याद्गुह्यतरं मया ।\nविमृश्यैतदशेषेण यथेच्छसि तथा कुरु ॥",
                    transliteration: "iti te jñānam ākhyātaṁ guhyād guhyataraṁ mayā\nvimṛśyaitad aśeṣeṇa yathecchasi tathā kuru",
                    translation: "Thus I have explained to you the most confidential of all knowledge. Deliberate on this fully, and then do as you wish.",
                    commentary: "In one of the most remarkable moments in all of scripture, the divine teacher does NOT command. After sharing the highest wisdom, Krishna says: now think about it, and decide for yourself. This is the ultimate respect for human free will.",
                    corporateWisdom: "The greatest leadership lesson in the Gita: after giving your team all the wisdom, information, and guidance you can — let them decide. True empowerment means trusting people to make their own choices. Counsel fully, then release control.",
                    familyWisdom: "This is the ultimate parenting verse: share your wisdom, give your best guidance, and then let your children choose their own path. The hardest and most loving act is saying 'now do as you wish' — and meaning it.",
                    themes: [.leadership, .succession, .familyBalance, .decisionMaking]
                ),
                Verse(
                    id: "18.66",
                    chapterId: 18,
                    verseNumber: 66,
                    sanskrit: "सर्वधर्मान्परित्यज्य मामेकं शरणं व्रज ।\nअहं त्वां सर्वपापेभ्यो मोक्षयिष्यामि मा शुचः ॥",
                    transliteration: "sarva-dharmān parityajya mām ekaṁ śaraṇaṁ vraja\nahaṁ tvāṁ sarva-pāpebhyo mokṣayiṣyāmi mā śucaḥ",
                    translation: "Abandon all varieties of duty and simply surrender unto me. I shall deliver you from all sinful reactions. Do not fear.",
                    commentary: "The Gita's final and most profound teaching: after all the philosophy, all the frameworks, all the practices — the ultimate path is surrender to a power greater than yourself. This is not fatalism; it is liberation.",
                    corporateWisdom: "After doing your absolute best — the analysis, the strategy, the execution — there comes a point where you must surrender to forces beyond your control. The market, technology shifts, black swan events. The CEO who can do their utmost and then let go sleeps well at night.",
                    familyWisdom: "Do your best for your family — love them, guide them, provide for them. And then surrender the outcome. You cannot control everything. The peace of a family built on love and surrender is unshakeable, even when life brings storms.",
                    themes: [.stressResilience, .selfMastery, .succession, .familyBalance]
                ),
                Verse(
                    id: "18.78",
                    chapterId: 18,
                    verseNumber: 78,
                    sanskrit: "यत्र योगेश्वरः कृष्णो यत्र पार्थो धनुर्धरः ।\nतत्र श्रीर्विजयो भूतिर्ध्रुवा नीतिर्मतिर्मम ॥",
                    transliteration: "yatra yogeśvaraḥ kṛṣṇo yatra pārtho dhanur-dharaḥ\ntatra śrīr vijayo bhūtir dhruvā nītir matir mama",
                    translation: "Wherever there is Krishna, the master of yoga, and wherever there is Arjuna, the supreme archer, there will certainly be fortune, victory, prosperity, and righteousness. This is my conviction.",
                    commentary: "The Gita's closing verse: where divine wisdom meets human capability, success is inevitable. It is the partnership of the eternal and the temporal that creates lasting victory.",
                    corporateWisdom: "When visionary wisdom (Krishna) meets disciplined execution (Arjuna), success follows. The best organisations pair strategic insight with operational excellence. Neither alone is sufficient — together, they are unstoppable.",
                    familyWisdom: "Where wisdom guides and dedication acts, families thrive. When partners combine one's vision with the other's practical strength — each contributing their best — the family becomes a force of prosperity and righteousness.",
                    themes: [.leadership, .strategicThinking, .succession]
                )
            ]
        )
    ]

    static var allVerses: [Verse] {
        chapters.flatMap { $0.verses }
    }

    static func versesForTheme(_ theme: WisdomTheme) -> [Verse] {
        allVerses.filter { $0.themes.contains(theme) }
    }

    static func searchVerses(query: String) -> [Verse] {
        let lowered = query.lowercased()
        return allVerses.filter { verse in
            verse.translation.lowercased().contains(lowered) ||
            verse.commentary.lowercased().contains(lowered) ||
            verse.corporateWisdom.lowercased().contains(lowered) ||
            verse.familyWisdom.lowercased().contains(lowered)
        }
    }

    static var dailyVerse: Verse {
        let dayOfYear = Calendar.current.ordinality(of: .day, in: .year, for: Date()) ?? 1
        let index = (dayOfYear - 1) % allVerses.count
        return allVerses[index]
    }

    static let appQuotes: [String] = [
        "You have the right to work, but never to the fruit of work.",
        "The mind is everything. What you think, you become.",
        "Change is the law of the universe.",
        "When meditation is mastered, the mind is unwavering like the flame of a lamp in a windless place.",
        "A person can rise through the efforts of their own mind; they can also degrade themselves. The mind is the friend and the enemy.",
        "There is neither this world, nor the world beyond, nor happiness for the one who doubts.",
        "The soul is neither born, and nor does it die.",
        "Set your heart upon your work but never its reward.",
        "Reshape yourself through the power of your will.",
        "The wise see knowledge and action as one."
    ]
}
